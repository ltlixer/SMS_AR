package cn.springmvc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.cisdijob.model.entity.Word;
import com.cisdijob.service.pages.FrequencyService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.utils.Distance;
import com.cisdijob.utils.yysimilarity.WordSimilarity;

public class TestMyBatis {
	/*private DropdownListService dorpdownListService;
	private WordService wordService;
	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml",
						"classpath:conf/spring-mybatis.xml" });
		dorpdownListService = (DropdownListService) context
				.getBean("dorpdownListService");
		wordService = (WordService)context.getBean("wordService");
	}

	@Test
	public void getDropdownList() {
		List<DropdownList> textDropdownList = dorpdownListService
				.getDropdownList(DropdownListAPI.textDropdownCode);
	}

	*//**
	 * 
	 * @param args
	 *            生字表的导入
	 * @throws IOException
	 *//*
	@Test
	public  void insertWrod() {
		String file = "F:\\创新基金\\wordTest.xls";
		WordExcel wordExcel = new WordExcel();
		List<Word> wordList = wordExcel.readWrodExcel(file);
		 
		for(Word word: wordList){
			wordService.insertWord(word);
		}
	}
*/
	private double alie =0.5;
	private WordService wordService;
	private FrequencyService frequencyService;
	
	@Test
	public void testYYSim(){
		WordSimilarity wordSimilarity = new WordSimilarity();
//		for(int i=0;i<50;i++){
//			
//			double yytemp = wordSimilarity.simWord("倍", "娇");
//			System.out.println(yytemp);
//		}
		System.out.println(wordSimilarity.simWord("背", "戳"));
		//readTxtFile("src/main/webapp/download/测试模型SMS_AR5.txt");
	}
	
	@Test
	public void testXLMX() throws IOException{
		com.cisdijob.utils.yysimilarity.WordSimilarity yyWordSimilarity = new com.cisdijob.utils.yysimilarity.WordSimilarity();
		Random random = new Random();
		String file = "src/main/webapp/download/测试模型SMS_AR0.txt";
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
					com.cisdijob.model.entity.WordSimilarity wordSimilarity = new com.cisdijob.model.entity.WordSimilarity();
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
	private void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        String word1 = lineTxt.split("\t")[0];
                        String word2 = lineTxt.split("\t")[2];
                        WordSimilarity wordSimilarity = new WordSimilarity();
                    	System.out.println(word1+" "+word2+" "+wordSimilarity.simWord(word1, word2));
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
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
