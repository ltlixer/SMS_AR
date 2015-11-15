package com.cisdijob.controller.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.ViewController;
import com.cisdijob.model.entity.DropdownList;
import com.cisdijob.model.entity.User;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.UserService;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.PaginationUtil;

@Controller
@RequestMapping(value="/homePage")
public class HomePageController extends ViewController {
	@Resource
	private WordSimilarityService wordSimilarityService;
	@Resource
	private UserService userService;
	@RequestMapping("/chart-analysis.html")
	public ModelAndView welcomePage(HttpServletRequest request) {
		ModelAndView mv = this.createLayoutView("homePage/chart-analysis.html");
		return mv;
	}
	@RequestMapping(value="/userAdmin.html")
	public ModelAndView userAdminPage(HttpServletRequest request) {
		ModelAndView mv = this.createLayoutView("homePage/userAdmin.html");
		return mv;
	}
	
	@RequestMapping(value="/score-statistic.html")
	public ModelAndView scoreStatisticPage(HttpServletRequest request) {
		ModelAndView mv = this.createLayoutView("homePage/score-statistic.html");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNumber", 1);
		map.put("perNumber", 10);
		List<WordSimilarity> wordSimilarityList = wordSimilarityService.getWordSimilarity(map);
		mv.addObject("wordSimilarityList", wordSimilarityList);
		mv.addObject(
				"pagination",
				PaginationUtil.getPaginationMap(1, 10,
						wordSimilarityService.getWordSimilarityCount(map)));
		List<User> gradePersonList = userService.getPersonList("gradePerson");
		mv.addObject("gradePersonList", gradePersonList);
		return mv;
	}
}
