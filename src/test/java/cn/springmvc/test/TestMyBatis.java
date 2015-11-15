package cn.springmvc.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cisdijob.model.entity.DropdownList;
import com.cisdijob.model.entity.Word;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.utils.DropdownListAPI;
import com.cisdijob.utils.WordExcel;

public class TestMyBatis {
	private DropdownListService dorpdownListService;
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

	/**
	 * 
	 * @param args
	 *            生字表的导入
	 * @throws IOException
	 */
	@Test
	public  void insertWrod() {
		String file = "F:\\创新基金\\wordTest.xls";
		WordExcel wordExcel = new WordExcel();
		List<Word> wordList = wordExcel.readWrodExcel(file);
		 
		for(Word word: wordList){
			wordService.insertWord(word);
		}
	}

	

}
