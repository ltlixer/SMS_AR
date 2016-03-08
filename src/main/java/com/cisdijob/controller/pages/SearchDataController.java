package com.cisdijob.controller.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.model.entity.Article;
import com.cisdijob.model.entity.Word;
import com.cisdijob.model.entity.WordScore;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.WordScoreService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.PaginationUtil;

@Controller
@RequestMapping(value = "SearchData")
public class SearchDataController {
	@Resource
	private WordService wordService;
	@Resource
	private WordSimilarityService wordSimilarityService;
	@Resource
	private ArticleService articleService;
	@RequestMapping(value = "WordInfoSearch")
	@ResponseBody
	public ModelAndView wordSearchPage(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Integer totalNum = wordService.getWordCount();
		Integer startNumber = (currPage - 1) * perPageNum;
		if (startNumber > totalNum & currPage > 1) {
			startNumber = (currPage - 2) * perPageNum;
		}
		Map<String, Object> map = params;

		map.put("startNumber", startNumber);
		map.put("perNumber", perPageNum);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/word-table");
		List<Word> wordList = wordService.getWordListByMap(map);
		mv.addObject("WORDLIST", wordList);
		return mv;
	}

	@RequestMapping(value = "WordInfoPagination")
	public ModelAndView wordPagePagination(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String, Object> map = PaginationUtil.getPaginationMap(currPage,
				perPageNum, wordService.getWordCount());
		if ((Integer) map.get("pages") < currPage && currPage > 1) {
			map.put("pageNum", currPage - 1);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/pagination");
		mv.addObject("pagination", map);
		return mv;
	}
	
	@RequestMapping(value = "WordSimilaritySearch")
	@ResponseBody
	public ModelAndView wordSimilaritySearchPage(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Integer totalNum = wordService.getWordCount();
		Integer startNumber = (currPage - 1) * perPageNum + 1;
		if (startNumber > totalNum & currPage > 1) {
			startNumber = (currPage - 2) * perPageNum + 1;
		}
		Map<String, Object> map = params;

		map.put("startNumber", startNumber);
		map.put("perNumber", perPageNum);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/wordSimilarity-table");
		List<WordSimilarity> wordSimilarityList = wordSimilarityService.getWordSimilarity(map);
		mv.addObject("wordSimilarityList", wordSimilarityList);
		return mv;
	}
	
	@RequestMapping(value = "WordSimilarityPagination")
	public ModelAndView wordSimilarityPagePagination(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String,Object> searchParams = new HashMap<String, Object>();
		Map<String, Object> map = PaginationUtil.getPaginationMap(currPage,
				perPageNum, wordSimilarityService.getWordSimilarityCount(searchParams));
		if ((Integer) map.get("pages") < currPage && currPage > 1) {
			map.put("pageNum", currPage - 1);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/pagination");
		mv.addObject("pagination", map);
		return mv;
	}
	
	@RequestMapping(value = "articleInfoSearch")
	@ResponseBody
	public ModelAndView articleSearchPage(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String,Object> totalNumMap = new HashMap<String, Object>();
		Integer totalNum = articleService.articleCount(totalNumMap);
		Integer startNumber = (currPage - 1) * perPageNum + 1;
		if (startNumber > totalNum & currPage > 1) {
			startNumber = (currPage - 2) * perPageNum + 1;
		}
		Map<String, Object> map = params;

		map.put("startNumber", startNumber);
		map.put("perNumber", perPageNum);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/articleManage-table");
		List<Article> articleList = articleService.getArticleListByMap(map);
		mv.addObject("articleList", articleList);
		return mv;
	}
	
	@RequestMapping(value = "articleInfoPagination")
	public ModelAndView articlePagePagination(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String,Object> searchParams = new HashMap<String, Object>();
		Map<String, Object> map = PaginationUtil.getPaginationMap(currPage,
				perPageNum, articleService.articleCount(searchParams));
		if ((Integer) map.get("pages") < currPage && currPage > 1) {
			map.put("pageNum", currPage - 1);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/pagination");
		mv.addObject("pagination", map);
		return mv;
	}
	
	/**
	 * wordScore分页
	 * @param request
	 * @param params
	 * @return
	 */
	@Resource
	private WordScoreService wordScoreService;
	@RequestMapping(value = "WordScoreSearch")
	@ResponseBody
	public ModelAndView wordScoreSearchPage(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Integer totalNum = wordScoreService.getCount();
		Integer startNumber = (currPage - 1) * perPageNum + 1;
		if (startNumber > totalNum & currPage > 1) {
			startNumber = (currPage - 2) * perPageNum + 1;
		}
		Map<String, Object> map = params;

		map.put("startNumber", startNumber);
		map.put("perNumber", perPageNum);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/wordScore-table");
		List<WordScore> wordScoreList = wordScoreService.getWordScore(map);
		mv.addObject("wordScoreList", wordScoreList);
		return mv;
	}
	@RequestMapping(value = "WordScorePagination")
	public ModelAndView wordScorePagePagination(HttpServletRequest request,
			@RequestBody Map<String, Object> params) {
		Integer currPage = Integer.parseInt(params.get("pageNum").toString());
		Integer perPageNum = Integer.parseInt(params.get("pagePerNumber")
				.toString());
		Map<String,Object> searchParams = new HashMap<String, Object>();
		Map<String, Object> map = PaginationUtil.getPaginationMap(currPage,
				perPageNum, wordScoreService.getCount());
		if ((Integer) map.get("pages") < currPage && currPage > 1) {
			map.put("pageNum", currPage - 1);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/pagination");
		mv.addObject("pagination", map);
		return mv;
	}

}
