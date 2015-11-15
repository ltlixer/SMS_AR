package com.cisdijob.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.cisdijob.model.entity.Word;
import com.cisdijob.service.impl.WordServiceImpl;
import com.cisdijob.service.pages.WordService;

public class WordExcel {
	public List<Word> readWrodExcel(String file){
		InputStream is = null;
		List<Word> wordList = new ArrayList<Word>();
		try {
			is = new FileInputStream(file);
			//Workbook wb = new HSSFWorkbook(fis);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			int rowNum = hssfSheet.getLastRowNum();
			for (int i = 0; i <= rowNum; i++) {
				Word word = new Word();
				HSSFRow hssfRow = hssfSheet.getRow(i);
				HSSFCell word1 = hssfRow.getCell(1);
				HSSFCell py = hssfRow.getCell(2);
				HSSFCell bs = hssfRow.getCell(5);
				HSSFCell bh = hssfRow.getCell(6);
				HSSFCell jg = hssfRow.getCell(7);
				word.setWord(getValue(word1));
				word.setPy(getValue(py));
				word.setBs(getValue(bs));
				word.setBh(getValue(bh));
				word.setJg(getValue(jg));
				wordList.add(word);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return wordList;
		// 构建一个excel2003工作簿
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
}
