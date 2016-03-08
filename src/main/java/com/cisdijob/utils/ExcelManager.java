package com.cisdijob.utils;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cisdijob.model.entity.ScoreCount;
import com.cisdijob.model.entity.Word;

public class ExcelManager {

	/**
	 * 将Word集合写入excel中
	 * 
	 * @param path
	 *            excel存放的路径
	 * @param wordList
	 *            需要写入excel中的Word集合
	 * @author lixer
	 */
	public static void writeeWordToExcel(String path, List<Word> wordList) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("wordlibrary");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("id");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("字");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("部首");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("结构");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("笔画");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("拼音");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("声调");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("声旁");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("频率");
		cell.setCellStyle(style);

		// 第五步，写入实体数据
		for (int i = 0; i < wordList.size(); i++) {
			Word word = wordList.get(i);
			row = sheet.createRow(i + 1);

			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(word.getId());

			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(word.getWord());

			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(word.getBs());

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(word.getJg());

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(word.getBh());

			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(word.getPy());

			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(word.getSd());

			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(word.getSp());

			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(word.getFrequency());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 2 qid:1 1:4 2:3 3:1 4:1 5:0.26666666666666666
	 * @param path
	 * @param scoreCounts_user
	 */
	public static void writeScoreCountQidToExcel(String path,
			List<ScoreCount> scoreCounts_user) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("wordsimilary");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("qid");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("articleId");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("newWord");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("newWordFrequency");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("matchedWord");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("matchedWordFrequency");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("user1");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("user2");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("user3");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("user4");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("pySimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("bhSimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(12);
		cell.setCellValue("bsSimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(13);
		cell.setCellValue("jgSimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(14);
		cell.setCellValue("yySimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(15);
		cell.setCellValue("yyFeatures");
		cell.setCellStyle(style);

		// 第五步，写入实体数据
		ScoreCount scoreCount = new ScoreCount(); 
        int qid_num=0;
        Map<String, Integer> map_word = new HashMap<String, Integer>();
		for (int i = 0; i < scoreCounts_user.size(); i++) {
			scoreCount = scoreCounts_user.get(i);
        	if(map_word.get(scoreCount.getNewWord())==null){
        		qid_num++;
        		map_word.put(scoreCount.getNewWord(), qid_num);
        	}
			row = sheet.createRow(i + 1);

			setCellValue(sheet, i + 1, 0,
					new HSSFRichTextString("qid:"+map_word.get(scoreCount.getNewWord())), style);
			setCellValue(sheet, i + 1, 1,
					new HSSFRichTextString(scoreCount.getArticleId()), style);
			setCellValue(sheet, i + 1, 2,
					new HSSFRichTextString(scoreCount.getNewWord()), style);
			setCellValue(sheet, i + 1, 3, new HSSFRichTextString(
					getFrequencyGrade(scoreCount.getNewWordFrequency())), style);
			setCellValue(sheet, i + 1, 4,
					new HSSFRichTextString(scoreCount.getMatchedWord()), style);
			setCellValue(sheet, i + 1, 5, new HSSFRichTextString(
					getFrequencyGrade(scoreCount.getMatchedWordFrequency())),
					style);
			setCellValue(sheet, i + 1, 6,
					new HSSFRichTextString(scoreCount.getScore_user1() + ""),
					style);
			setCellValue(sheet, i + 1, 7,
					new HSSFRichTextString(scoreCount.getScore_user2() + ""),
					style);
			setCellValue(sheet, i + 1, 8,
					new HSSFRichTextString(scoreCount.getScore_user3() + ""),
					style);
			setCellValue(sheet, i + 1, 9,
					new HSSFRichTextString(scoreCount.getScore_user4() + ""),
					style);
			setCellValue(sheet, i + 1, 10,
					new HSSFRichTextString("1:"+scoreCount.getPySimilarity()),
					style);
			setCellValue(sheet, i + 1, 11,
					new HSSFRichTextString("2:"+scoreCount.getBhSimilarity()),
					style);
			setCellValue(sheet, i + 1, 12,
					new HSSFRichTextString("3:"+scoreCount.getBsSimilarity()),
					style);
			setCellValue(sheet, i + 1, 13,
					new HSSFRichTextString("4:"+scoreCount.getJgSimilarity()),
					style);
			setCellValue(sheet, i + 1, 14,
					new HSSFRichTextString("5:"+scoreCount.getYySimilarity()),
					style);
			setCellValue(sheet, i + 1, 15,
					new HSSFRichTextString("6:"+scoreCount.getYyFeatures()),
					style);
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *1541	一年级下_第10课	后	A	句	D	2	2	2	2	4	3	1	1	0.26666666666666666
	 *
	 * @param path
	 * @param scoreCounts_user
	 */
	public static void writeScoreCountToExcel(String path,
			List<ScoreCount> scoreCounts_user) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("wordsimilary");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("qid");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("articleId");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("newWord");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("newWordFrequency");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("matchedWord");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("matchedWordFrequency");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("user1");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("user2");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("user3");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("user4");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("pySimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("bhSimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(12);
		cell.setCellValue("bsSimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(13);
		cell.setCellValue("jgSimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(14);
		cell.setCellValue("yySimilarity");
		cell.setCellStyle(style);
		cell = row.createCell(15);
		cell.setCellValue("yyFeatures");
		cell.setCellStyle(style);

		// 第五步，写入实体数据
		for (int i = 0; i < scoreCounts_user.size(); i++) {
			ScoreCount scoreCount = scoreCounts_user.get(i);
			row = sheet.createRow(i + 1);

			setCellValue(sheet, i + 1, 0,
					new HSSFRichTextString(scoreCount.getQid()), style);
			setCellValue(sheet, i + 1, 1,
					new HSSFRichTextString(scoreCount.getArticleId()), style);
			setCellValue(sheet, i + 1, 2,
					new HSSFRichTextString(scoreCount.getNewWord()), style);
			setCellValue(sheet, i + 1, 3, new HSSFRichTextString(
					getFrequencyGrade(scoreCount.getNewWordFrequency())), style);
			setCellValue(sheet, i + 1, 4,
					new HSSFRichTextString(scoreCount.getMatchedWord()), style);
			setCellValue(sheet, i + 1, 5, new HSSFRichTextString(
					getFrequencyGrade(scoreCount.getMatchedWordFrequency())),
					style);
			setCellValue(sheet, i + 1, 6,
					new HSSFRichTextString(scoreCount.getScore_user1() + ""),
					style);
			setCellValue(sheet, i + 1, 7,
					new HSSFRichTextString(scoreCount.getScore_user2() + ""),
					style);
			setCellValue(sheet, i + 1, 8,
					new HSSFRichTextString(scoreCount.getScore_user3() + ""),
					style);
			setCellValue(sheet, i + 1, 9,
					new HSSFRichTextString(scoreCount.getScore_user4() + ""),
					style);
			setCellValue(sheet, i + 1, 10,
					new HSSFRichTextString(scoreCount.getPySimilarity() + ""),
					style);
			setCellValue(sheet, i + 1, 11,
					new HSSFRichTextString(scoreCount.getBhSimilarity() + ""),
					style);
			setCellValue(sheet, i + 1, 12,
					new HSSFRichTextString(scoreCount.getBsSimilarity() + ""),
					style);
			setCellValue(sheet, i + 1, 13,
					new HSSFRichTextString(scoreCount.getJgSimilarity() + ""),
					style);
			setCellValue(sheet, i + 1, 14,
					new HSSFRichTextString(scoreCount.getYySimilarity() + ""),
					style);
			setCellValue(sheet, i + 1, 15,
					new HSSFRichTextString(scoreCount.getYyFeatures() + ""),
					style);
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setCellValue(HSSFSheet sheet, int iRow, int iCol,
			HSSFRichTextString val, HSSFCellStyle style) {
		HSSFRow row = sheet.getRow(iRow);
		HSSFCell cell = row.createCell(iCol);
		cell.setCellStyle(style);
		cell.setCellValue(val);
	}

	private static String getFrequencyGrade(int frequency) {
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
