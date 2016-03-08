package com.cisdijob.controller.pages;

import java.io.IOException;
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

import com.cisdijob.config.Permission;
import com.cisdijob.controller.common.ViewController;
import com.cisdijob.model.entity.Article;
import com.cisdijob.model.entity.ScoreCount;
import com.cisdijob.model.entity.User;
import com.cisdijob.model.entity.WordScore;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.UserService;
import com.cisdijob.service.pages.WordScoreService;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.ExcelManager;
import com.cisdijob.utils.FileDownload;
import com.cisdijob.utils.PaginationUtil;

@Controller
@RequestMapping(value="/homePage")
public class HomePageController extends ViewController {
	@Resource
	private WordSimilarityService wordSimilarityService;
	@Resource
	private WordScoreService wordScoreService;
	@Resource
	private UserService userService;
	@Resource
	private ArticleService articleService;
	@RequestMapping("/chart-analysis.html")
	public ModelAndView welcomePage(HttpServletRequest request) {
		ModelAndView mv = this.createLayoutView("homePage/chart-analysis.html");
		return mv;
	}
	@RequestMapping(value="/userAdmin.html")
	public ModelAndView userAdminPage(HttpServletRequest request) {
		ModelAndView mv = this.createLayoutView("homePage/userAdmin.html");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNumber", 1);
		map.put("perNumber", 10);
		List<User> userList = userService.getPersonList(Permission.TEACHER);
		userList.addAll(userService.getPersonList(Permission.STUDENT));
		mv.addObject("USERLIST", userList);
		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						userList.size()));
		return mv;
	}
	
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> parmas) {
		String userId = parmas.get("userId").toString();
		System.out.println(userId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.deleteUser(userId);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("msg", "该用户有打分记录，不能删除！");
		}
		return result;
	}
	
	@RequestMapping(value="/score-statistic.html")
	public ModelAndView scoreStatisticPage(HttpServletRequest request) {
		ModelAndView mv = this.createLayoutView("homePage/score-statistic.html");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNumber", 1);
		map.put("perNumber", 10);
		List<WordScore> wordScoreList = wordScoreService.getWordScore(map);
		mv.addObject("wordScoreList", wordScoreList);
		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						wordScoreService.getCount()));
		List<Article> articleList = articleService.getArticleList();
		List<User> gradePersonList = userService.getPersonList("teacher");
		mv.addObject("gradePersonList", gradePersonList);
		mv.addObject("articleList", articleList);
		return mv;
	}
	
	/**
	 * 导出打分统计提供下载
	 * @param request
	 * @param response
	 * @param parmas
	 * @throws IOException 
	 */
	@RequestMapping(value="/downloadScoreCount")
	public void downloadScoreCount(HttpServletRequest request,HttpServletResponse response) throws IOException {

		List<ScoreCount> scoreCounts_user = wordScoreService.getAllUserScore();
		
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/download/";
		String name = "打分统计SMS_AR.xls";
		ExcelManager.writeScoreCountToExcel(path+name, scoreCounts_user);
		
		FileDownload.downLoadFile(response, path, name);	
	}
	/**
	 * 导出训练模型提供下载
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadModel")
	public void downloadModel(HttpServletRequest request,HttpServletResponse response) throws IOException {

		List<ScoreCount> scoreCounts_user = wordScoreService.getAllUserScore();
		
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/download/";
		String name = "训练模型SMS_AR.xls";
		ExcelManager.writeScoreCountQidToExcel(path+name, scoreCounts_user);
		
		FileDownload.downLoadFile(response, path, name);	
	}
	
	/**
	 * 导出随机打分统计提供下载
	 * @param request
	 * @param response
	 * @param parmas
	 * @throws IOException 
	 */
	@RequestMapping(value="/downloadRandomScoreCount")
	public void downloadRandomScoreCount(HttpServletRequest request,HttpServletResponse response) throws IOException {

		List<ScoreCount> scoreCounts_user = wordScoreService.getRandomAllUserScore();
		
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/download/";
		String name = "随机打分统计SMS_AR.xls";
		ExcelManager.writeScoreCountToExcel(path+name, scoreCounts_user);
		
		FileDownload.downLoadFile(response, path, name);	
	}
	/**
	 * 导出随机训练模型提供下载
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadRandomModel")
	public void downloadRandomModel(HttpServletRequest request,HttpServletResponse response) throws IOException {

		List<ScoreCount> scoreCounts_user = wordScoreService.getRandomAllUserScore();
		
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "/download/";
		String name = "随机训练模型SMS_AR.xls";
		ExcelManager.writeScoreCountQidToExcel(path+name, scoreCounts_user);
		
		FileDownload.downLoadFile(response, path, name);	
	}
	
	public String getFrequencyGrade(int frequency){
		if(frequency>=1 && frequency<=150){
			return "A";
		}else if(frequency>150 && frequency<=300){
			return "B";
		}else if(frequency>300 && frequency<=500){
			return "C";
		}else if(frequency>500 && frequency<=1000){
			return "D";
		}else if(frequency>1000 && frequency<=1500){
			return "E";
		}else if(frequency>1500 && frequency<=2000){
			return "F";
		}else if(frequency>2000 && frequency<=2500){
			return "G";
		}else if(frequency>2500 && frequency<=3000){
			return "H";
		}else if(frequency>3000 && frequency<=3500){
			return "I";
		}else if(frequency>3500 && frequency<=4000){
			return "J";
		}else if(frequency>4000 && frequency<=4500){
			return "K";
		}else if(frequency>4500 && frequency<=5000){
			return "L";
		}else if(frequency>5000 && frequency<=5500){
			return "M";
		}else if(frequency>5500 && frequency<=6000){
			return "N";
		}else if(frequency>6000 && frequency<=6500){
			return "O";
		}else if(frequency>6500 && frequency<=6763){
			return "P";
		}else{
			return "0";
		}
	}
}


