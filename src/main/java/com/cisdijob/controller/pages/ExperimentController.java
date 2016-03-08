package com.cisdijob.controller.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.ViewController;
import com.cisdijob.model.entity.ScoreCount;
import com.cisdijob.model.entity.Word;
import com.cisdijob.model.entity.WordScore;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.FrequencyService;
import com.cisdijob.service.pages.QuestionService;
import com.cisdijob.service.pages.WordScoreService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.Distance;
import com.cisdijob.utils.ExcelManager;

@Controller
@RequestMapping(value = "/experiment")
public class ExperimentController extends ViewController {
	@Resource
	private WordService wordService;
	@Resource
	private ArticleService articleService;
	@Resource
	private DropdownListService dropdownListService;
	@Resource
	private WordScoreService wordScoreService;
	@Resource
	private QuestionService questionService;
	@Resource
	private FrequencyService frequencyService;
	@Resource
	private WordSimilarityService wordSimilarityService;

	@RequestMapping(value = "/addSd")
	public void choiceHomework() {
		List<Word> wordList = wordService.getWordList();
		int i = 1;
		for (Word word : wordList) {
			String s = word.getPy();
			List<String> ss = new ArrayList<String>();
			for (String sss : s.replaceAll("[^0-9]", ",").split(",")) {
				if (sss.length() > 0)
					ss.add(sss);
			}
			if (ss.isEmpty()) {
				word.setSd("0");
			} else {
				word.setSd(ss.get(0));
			}
			wordService.updateWord(word);
		}
	}

	@RequestMapping(value = "/xunlianmoxing100")
	public void xunlianmoxing100() throws IOException {
		
		Random random = new Random();
		String file = "src/main/webapp/upload/100个音近形近字.xls";
		InputStream is = null;

		List<String> wordList1 = new ArrayList<String>();
		List<String> wordList2 = new ArrayList<String>();
		try {
			is = new FileInputStream(file);
			// Workbook wb = new HSSFWorkbook(fis);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			int rowNum = hssfSheet.getLastRowNum();
			for (int i = 0; i <= rowNum; i++) {

				HSSFRow hssfRow = hssfSheet.getRow(i);
				HSSFCell newWord = hssfRow.getCell(0);
				HSSFCell matchedWord = hssfRow.getCell(1);

				wordList1.add(getValue(newWord));
				wordList2.add(getValue(matchedWord));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		for (int j = 0; j < wordList1.size(); j++) {
			FileWriter fileWriter = new FileWriter(
					"src/main/webapp/download/测试模型SMS_AR" + j + ".txt");

			String w = wordList1.get(j);
			List<String> randomWordList = new ArrayList<String>();
			for (int i = 0; i < 50;) {
				String word = wordList2.get((Math.abs(random.nextInt()))
						% wordList2.size());
				if (!randomWordList.contains(word)) {
					if (i == 0) {
						randomWordList.add(wordList2.get(j));
						i++;
					} else {
						randomWordList.add(word);
						i++;
					}
				}
			}
			
			for (int i = 0; i < 50; i++) {
				Word word1 = wordService.getWord(w);
				Word word2 = wordService.getWord(randomWordList.get(i));

				if (word1 != null && word2 != null) {
					WordSimilarity wordSimilarity = new WordSimilarity();
					wordSimilarity.setNewWord(word1.getWord());
					wordSimilarity.setMatchedWord(word2.getWord());
					wordSimilarity.setNewWordFrequency(frequencyService
							.getFrequencyByWord(word1.getWord())
							.getFrequencyId());
					wordSimilarity.setMatchedWordFrequency(frequencyService
							.getFrequencyByWord(word2.getWord())
							.getFrequencyId());
					wordSimilarity.setPySimilarity(Distance.getEditDistance(
							word1.getPy(), word2.getPy()));
					wordSimilarity.setBhSimilarity(Distance.getEditDistance(
							word1.getBh(), word2.getBh()));
					wordSimilarity.setBsSimilarity(word1.getBs().equals(
							word2.getBs()) ? 1 : 0);
					wordSimilarity.setJgSimilarity(word1.getJg().equals(
							word2.getJg()) ? 1 : 0);
					com.cisdijob.utils.yysimilarity.WordSimilarity yyWordSimilarity = new com.cisdijob.utils.yysimilarity.WordSimilarity();
					double tempyy = yyWordSimilarity.simWord(word1.getWord(), word2.getWord());
					wordSimilarity.setYySimilarity(tempyy);
					fileWriter.write(wordSimilarity.getNewWord()
							+ "\t"
							+ getFrequencyGrade(wordSimilarity
									.getNewWordFrequency())
							+ "\t"
							+ wordSimilarity.getMatchedWord()
							+ "\t"
							+ getFrequencyGrade(wordSimilarity
									.getMatchedWordFrequency()) + "\t"
							+ wordSimilarity.getPySimilarity() + "\t"
							+ wordSimilarity.getBhSimilarity() + "\t"
							+ wordSimilarity.getBsSimilarity() + "\t"
							+ wordSimilarity.getJgSimilarity() + "\t"
							+ wordSimilarity.getYySimilarity() + "\r\n");
					fileWriter.flush();
				}
			}

			fileWriter.close();
		}

	}

	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public String getFrequencyGrade(int frequency) {
		if (frequency >= 1 && frequency <= 150) {
			return "A";
		} else if (frequency > 150 && frequency <= 300) {
			return "B";
		} else if (frequency > 300 && frequency <= 500) {
			return "C";
		} else if (frequency > 500 && frequency <= 1000) {
			return "D";
		} else if (frequency > 1000 && frequency <= 1500) {
			return "E";
		} else if (frequency > 1500 && frequency <= 2000) {
			return "F";
		} else if (frequency > 2000 && frequency <= 2500) {
			return "G";
		} else if (frequency > 2500 && frequency <= 3000) {
			return "H";
		} else if (frequency > 3000 && frequency <= 3500) {
			return "I";
		} else if (frequency > 3500 && frequency <= 4000) {
			return "J";
		} else if (frequency > 4000 && frequency <= 4500) {
			return "K";
		} else if (frequency > 4500 && frequency <= 5000) {
			return "L";
		} else if (frequency > 5000 && frequency <= 5500) {
			return "M";
		} else if (frequency > 5500 && frequency <= 6000) {
			return "N";
		} else if (frequency > 6000 && frequency <= 6500) {
			return "O";
		} else if (frequency > 6500 && frequency <= 6763) {
			return "P";
		} else {
			return "0";
		}
	}
}
