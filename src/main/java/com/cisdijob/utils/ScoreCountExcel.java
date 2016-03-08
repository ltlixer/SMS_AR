package com.cisdijob.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cisdijob.model.entity.ScoreCount;

public class ScoreCountExcel {
	/**
	 * 读取《打分模型SMS_AR.xls》这个文件
	 * @param file
	 * @return
	 */
	public List<Map<String, String>> readScoreCountExcel(String file){
		InputStream is = null;
		List<Map<String, String>> scoreCountList = new ArrayList<Map<String, String>>();
		try {
			is = new FileInputStream(file);
			//Workbook wb = new HSSFWorkbook(fis);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			int rowNum = hssfSheet.getLastRowNum();
			for (int i = 1; i <= rowNum; i++) {
				Map<String, String> scoreCount = new HashMap<String, String>();
				HSSFRow hssfRow = hssfSheet.getRow(i);
				HSSFCell score = hssfRow.getCell(0);
				HSSFCell qid = hssfRow.getCell(1);
				HSSFCell articleId = hssfRow.getCell(2);
				HSSFCell newWord = hssfRow.getCell(3);
				HSSFCell matchedWord = hssfRow.getCell(5);
				HSSFCell pySimilarity = hssfRow.getCell(7);
				HSSFCell bhSimilarity = hssfRow.getCell(8);
				HSSFCell bsSimilarity = hssfRow.getCell(9);
				HSSFCell jgSimilarity = hssfRow.getCell(10);
				HSSFCell yySimilarity = hssfRow.getCell(11);
				
				scoreCount.put("qid",getValue(qid));
				scoreCount.put("articleId",getValue(articleId));
				scoreCount.put("newWord",getValue(newWord));
				scoreCount.put("matchedWord",getValue(matchedWord));
				scoreCount.put("score",String.valueOf(new Double(getValue(score).trim()).intValue()));
				scoreCount.put("pySimilarity",getValue(pySimilarity).trim());
				scoreCount.put("bhSimilarity",getValue(bhSimilarity).trim());
				scoreCount.put("bsSimilarity",getValue(bsSimilarity).trim());
				scoreCount.put("jgSimilarity",getValue(jgSimilarity).trim());
				scoreCount.put("yySimilarity",getValue(yySimilarity).trim());
				scoreCountList.add(scoreCount);
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
		return scoreCountList;
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
