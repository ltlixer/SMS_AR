package com.cisdijob.controller.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.config.FilePathConfig;
import com.cisdijob.controller.common.ViewController;
import com.cisdijob.model.entity.Article;
import com.cisdijob.model.entity.DropdownList;
import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.User;
import com.cisdijob.model.entity.Word;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.QuestionService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.utils.DropdownListAPI;
import com.cisdijob.utils.ExcelManager;
import com.cisdijob.utils.FileDownload;
import com.cisdijob.utils.FileUpload;
import com.cisdijob.utils.HttpServletUtil;
import com.cisdijob.utils.PaginationUtil;
import com.cisdijob.utils.ReadText;
import com.cisdijob.utils.ScoreCountExcel;
import com.cisdijob.utils.WordExcel;

@Controller
@RequestMapping(value = "/configManage")
public class ConfigManageController extends ViewController {
	@Resource
	private WordService wordService;
	@Resource
	private ArticleService articleService;
	@Resource
	private DropdownListService dropdownListService;
	@Resource
	private QuestionService questionService;

	@RequestMapping(value = "/article-management.html")
	public ModelAndView articleConfig() {
		ModelAndView mv = this
				.createLayoutView("configManage/article-management.html");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNumber", 1);
		map.put("perNumber", 10);
		List<Article> articleList = articleService.getArticleListByMap(map);
		List<DropdownList> firstClassList = dropdownListService
				.getDropdownList(DropdownListAPI.textType);
		mv.addObject("articleList", articleList);
		mv.addObject("firstClassList", firstClassList);
		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						articleService.articleAllCounts()));
		return mv;
	}

	@RequestMapping(value = "/secondClass")
	@ResponseBody
	public ModelAndView secondClassDropdownList(HttpServletRequest request,
			@RequestBody Map<String, Object> parmas) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/articleTypeSelect-fragment");
		String code = (String) parmas.get("code");
		List<DropdownList> firstClassList = dropdownListService
				.getDropdownList(DropdownListAPI.textType);
		List<DropdownList> secondClassList = dropdownListService
				.getDropdownListTwo(code);
		mv.addObject("firstClassList", firstClassList);
		mv.addObject("secondClassList", secondClassList);
		mv.addObject("firstSelectCode", code);
		return mv;
	}

	@RequestMapping(value = "/articleUpload")
	public ModelAndView articleUpload(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		ModelAndView mv = new ModelAndView();
		String firstClassCode = request.getParameter("FirstClass").toString();
		String secondClassCode = request.getParameter("SecondClass").toString();
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/upload/";
		String fileNamePath = FileUpload.upload_path(request, path);
		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");

		String uuid = FileUpload.getFileName(request).split(".txt")[0];
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		// 解析文档
		Map<String, Object> map = ReadText.getSelectFile(fileNamePath);
		String content = map.get("content").toString();
		// content = content.replaceAll("<br/>", "");
		String array[] = (String[]) map.get("word");
		Article article = new Article();
		article.setId(uuid);
		article.setTitle(map.get("title").toString());
		article.setContent(content);
		article.setType(firstClassCode);
		article.settype2(secondClassCode);
		article.setCreatePerson(user.getName());
		article.setCreateTime(time);
		try {
			articleService.insertArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// question
		List<Question> questionList = ReadText.getIntelligentQuestion(content,
				array);
		for (Question q : questionList) {
			q.setArticleId(uuid);
			q.setCreatePerson(user.getName());
			q.setCreateTime(time);
			try {
				questionService.insertQuestion(q);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.setViewName("forward:/configManage/article-management.html");
		return mv;
	}

	@RequestMapping(value = "/deleteArticle")
	@ResponseBody
	public Map<String, Object> deleteArticle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> parmas) {
		String articleId = parmas.get("articleId").toString();
		System.out.println(articleId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			articleService.deleteArticle(articleId);
			result.put("msg", "成功删除《" + articleId + "》这篇课文！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("msg", "课文删除失败!");
		}
		return result;
	}

	@RequestMapping(value = "/word.html")
	public ModelAndView wordPage() {
		ModelAndView mv = this.createLayoutView("configManage/word.html");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNumber", 0);
		map.put("perNumber", 10);
		List<Word> wordList = wordService.getWordListByMap(map);
		mv.addObject("WORDLIST", wordList);

		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						wordService.getWordCount()));
		return mv;
	}

	@RequestMapping(value = "/readWordExcel")
	public ModelAndView wordExcelPage(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/upload/";
		FileUpload.upload(request, path);
		ModelAndView mv = new ModelAndView();
		WordExcel wordExcel = new WordExcel();
		String file = path + FilePathConfig.wordLibraryFileName;
		try {
			List<Word> wordList = wordExcel.readWrodExcel(file);
			for (Word word : wordList) {
				try {
					wordService.insertWord(word);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("“" + word.getWord() + "”在字库表中已经存在！");
					e.printStackTrace();
				}
			}
			mv.setViewName("forward:/configManage/word.html");
			// result.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// result.put("errorMsg", "数据存储错误！");
		}
		return mv;
	}
	
	@RequestMapping(value = "/downloadWordLibrary")
	public ModelAndView downloadWordLibrary(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		ModelAndView mv = new ModelAndView();
		
		List<Word> wordList = wordService.getWordList();
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/download/";
		String name = "字库表.xls";
		ExcelManager.writeeWordToExcel(path+name, wordList);
		
		//提供下载
		FileDownload.downLoadFile(response, path, name);
		return mv;
	}	

	@RequestMapping(value = "/emptyWord")
	@ResponseBody
	public Map<String, Object> deleteWord(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			wordService.deletWord();
			result.put("success", true);
			result.put("successMsg", "清空成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("errorMsg", "清空成功！");
		}
		return result;
	}

	@RequestMapping(value = "/tools.html")
	public ModelAndView tools(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		ModelAndView mv = this.createLayoutView("configManage/tools.html");

		return mv;
	}

	@RequestMapping(value = "/transfer")
	public void transfer(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		
		//将上传的文件《打分模型SMS_AR.xls》保存到upload里面
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/upload/";
		FileUpload.upload(request, path);

		//将数据写入《打分模型SMS_AR.txt》中，放在download里面
		List<Map<String, String>> scoreCountList = new ScoreCountExcel().readScoreCountExcel(path+"打分模型SMS_AR.xls");
		FileWriter fileWriter=new FileWriter(request.getSession().getServletContext().getRealPath("/")+"/download/打分模型SMS_AR.txt");
		for (Map<String, String> sc:scoreCountList) {
			fileWriter.write(sc.get("score")+" "+sc.get("qid")
					+" "+sc.get("pySimilarity")+" "+sc.get("bhSimilarity")
					+" "+sc.get("bsSimilarity")+" "+sc.get("jgSimilarity")
					+" "+sc.get("yySimilarity")+"\r\n");
		}
		fileWriter.flush();
		fileWriter.close();
		
		//提供下载《打分模型SMS_AR.txt》
		FileDownload.downLoadFile(response, request.getSession().getServletContext().getRealPath("/")+"/download/", "打分模型SMS_AR.txt");
	}
	
}
