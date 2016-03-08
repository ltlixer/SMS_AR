package com.cisdijob.controller.pages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.ViewController;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.FrequencyService;
import com.cisdijob.service.pages.QuestionService;
import com.cisdijob.service.pages.WordScoreService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.service.pages.WordSimilarityService;

@Controller
@RequestMapping(value = "/homework")
public class HomeworkController extends ViewController {
	@Resource
	private WordService wordService;
	@Resource
	private ArticleService articleService;
	@Resource
	private DropdownListService dropdownListService;
	@Resource
	private WordScoreService wordScoreService;
	@Resource
	private QuestionService questionService;
	@Resource
	private FrequencyService frequencyService;
	@Resource
	private WordSimilarityService wordSimilarityService;
	@RequestMapping(value = "/factual-homework.html")
	public ModelAndView factualHomework() {
		ModelAndView mv = this.createLayoutView("homework/factual-homework.html");
		
		
		return mv;
	}
	
	@RequestMapping(value = "/choice-homework.html")
	public ModelAndView choiceHomework() {
		ModelAndView mv = this.createLayoutView("homework/choice-homework.html");
		
		return mv;
	}
	
}
