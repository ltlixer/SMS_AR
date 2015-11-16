package com.cisdijob.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.ss.formula.functions.T;

import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.ReadLine;
public class ReadText {
	private static InputStreamReader isr=null;
	private static BufferedReader br=null;
	private static Map<String ,String> m=new HashMap<String,String >();
	
	public static Map<String,Object> getSelectFile(String path){
		Map<String,Object> map = new HashMap<String, Object>();
		List<ReadLine> readLine = new ArrayList<ReadLine>();
		String title = "";
		String word = "";
		String content="";
		int i=0;
		try {
			isr=new InputStreamReader(new FileInputStream(path));
			br=new BufferedReader(isr);
			String file;	
			while((file=br.readLine())!=null){
				ReadLine  line = new ReadLine();
				line.setLineScentence(file);
				readLine.add(line);
			}
			
			for(ReadLine rl:readLine){
				if(i==0){
					title = rl.getLineScentence();
				}else if(i==readLine.size()-1){
					word = rl.getLineScentence();
				}else{
					content+=rl.getLineScentence()+"<br/>";
				}
				i++;
			}
			/*int length = str.length();
			int first_index = str.indexOf("<br/>");
			int last_index = str.lastIndexOf("<br/>");
			String title = str.substring(0, first_index);
			String content_word = str.substring(first_index+5,last_index);
			char array[] = (str.substring(last_index, length-1)).toCharArray();*/
			String array[] = word.split(" ");
			map.put("title", title);
			map.put("content", content);
			map.put("word", array);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			close();
		}
		return map;
	}
	public static void close(){
		try {
			if(br!=null){
				br.close();
				br=null;
			}
			if(isr!=null){
				isr.close();
				isr=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @author gjp
	 * @param content是文章的内容  array[]是生字数组
	 */
	public static List<Question> getIntelligentQuestion(String content,String array[]){
		List<Question> questionList = new ArrayList<Question>();
		String scentence[] = content.split("。");
		String question = "";
		int flag = 1;
		for(int i=0;i<array.length;i++){
			String word = array[i];
			for(int j=0;j<scentence.length;j++){
				question = scentence[j];
				if(question.contains(word)){
					question = question.replaceFirst(word, "("+word+")");
					Question que = new Question();
					que.setQuestion(flag+"."+question+"。");
					que.setNewWord(word);
					questionList.add(que);
					flag++;
				}
			}
		}
		return questionList;
	}
}
