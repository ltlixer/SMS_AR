package com.cisdijob.utils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.ReadLine;
public class ReadText {
	private static InputStreamReader isr=null;
	private static BufferedReader br=null;
	public static Map<String,Object> getSelectFile(String path){
		Map<String,Object> map = new HashMap<String, Object>();
		List<ReadLine> readLine = new ArrayList<ReadLine>();
		String title = "";
		String word = "";
		String content="";
		int i=0;
		try {
			isr=new InputStreamReader(new FileInputStream(path),"utf-8");
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
			
			String[] wordArray = word.split(" ");
			Set<String>  set = new HashSet<String>();
			String result = "";
			//删除生字数组中重复的字
			for( String s : wordArray){
		        String s2 = (s.split("="))[0].trim();             //取“=”号前面的部分
		        if(set.add(s2)){
		                result += s+",";
		        }        
			}
			result = result.substring(0,result.length()-1); 
			String[] array = result.split(",");
			
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
	 * @throws UnsupportedEncodingException 
	 */
	public static List<Question> getIntelligentQuestion(String content,String array[]) throws UnsupportedEncodingException{
		List<Question> questionList = new ArrayList<Question>();
		String txt = content.replace("<br/>", "").trim();
		String xmlString = HttpRequest.sendGet("http://api.ltp-cloud.com/analysis/", "api_key=05E3D1F0QDfA4Wyj4nXLdtooNLlrad9tWLZz9S3W&text="+txt+"&pattern=all&format=xml");
		StringReader sr = new StringReader(xmlString);  
		InputSource is = new InputSource(sr);   
		  
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		  
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
		Document doc = null;
		try {
			doc = builder.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        
		NodeList ele = doc.getElementsByTagName("sent");
		String[] scentence = new String[ele.getLength()];
		for(int i=0;i<ele.getLength();i++){
			scentence[i]=ele.item(i).getAttributes().getNamedItem("cont").getTextContent().toString();
		}
		
		String question = "";
		int flag = 1;
		for(int i=0;i<array.length;i++){
			String word = array[i];
			int lable = 1;
			for(int j=0;j<scentence.length;j++){
				
				if(lable>=2){
					break;
				}
				question = scentence[j];
				if(question.contains(word)){
					question = question.replaceFirst(word, "("+word+")");
					Question que = new Question();
					que.setQuestion(flag+"."+question+"。");
					que.setNewWord(word);
					questionList.add(que);
					flag++;
					lable++;
				}
			}
		}
		return questionList;
	}
}
