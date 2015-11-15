package com.cisdijob.controller.pages;

import java.util.ArrayList;
import java.util.Collections;
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
import com.cisdijob.model.entity.DistanceAndWord;
import com.cisdijob.model.entity.DropdownList;
import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.User;
import com.cisdijob.model.entity.Word;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.QuestionService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.FileUpload;
import com.cisdijob.utils.PaginationUtil;
import com.cisdijob.utils.Distance;
import com.cisdijob.utils.DropdownListAPI;
import com.cisdijob.utils.HttpServletUtil;
import com.cisdijob.utils.WordExcel;

@Controller
@RequestMapping(value = "/factual")
public class FactualController extends ViewController {
	@Resource
	private DropdownListService dorpdownListService;
	@Resource
	private ArticleService articleService;
	@Resource
	private QuestionService questionService;
	@Resource
	private WordService wordService;
	@Resource
	private WordSimilarityService wordSimilarityService;

	@RequestMapping(value = "/score.html")
	public ModelAndView scorePage() {
		ModelAndView mv = this.createLayoutView("factual/score.html");
		List<DropdownList> textDropdownList = dorpdownListService
				.getDropdownList(DropdownListAPI.textDropdownCode);
		Article article = articleService.getArticleById(1);
		List<Question> questionList = questionService
				.getQuestionListByArticleId(1);
		mv.addObject("TEXTLIST", textDropdownList);
		mv.addObject("article", article);
		mv.addObject("questionList", questionList);
		return mv;
	}

	@RequestMapping(value = "/factual-question.html")
	public ModelAndView factualQuestionPage() {
		ModelAndView mv = this
				.createLayoutView("factual/factual-question.html");
		return mv;
	}

	@RequestMapping(value = "/text-system.html")
	public ModelAndView textSystemPage() {
		ModelAndView mv = this.createLayoutView("factual/folderTree.html");
		return mv;
	}

	/**
	 * 
	 * @param request
	 * @param parmas
	 * @author gjp date 2015-10-26 组合方式 bs-->jg-->bh-->py
	 */
	@RequestMapping(value = "/articleSelect")
	@ResponseBody
	public ModelAndView articleSelect(HttpServletRequest request,
			@RequestBody Map<String, Object> parmas) {
		String articleId = parmas.get("articleId").toString();
		int id = Integer.parseInt(articleId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/article-fragment");
		List<DropdownList> textDropdownList = dorpdownListService
				.getDropdownList(DropdownListAPI.textDropdownCode);
		Article article = articleService.getArticleById(id);
		List<Question> questionList = questionService
				.getQuestionListByArticleId(id);
		List<Word> wordList = wordService.getWordList();

		List<DistanceAndWord> py_distance = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> bh_distance = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> bh_rank = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> py_rank = new ArrayList<DistanceAndWord>();
		// List<Word> map_word = new ArrayList<Word>();
		Map<Integer, List<Word>> map_word = new HashMap<Integer, List<Word>>();
		for (Question question : questionList) {
			List<Word> temp = new ArrayList<Word>();
			List<Word> bs = new ArrayList<Word>();
			List<Word> jg = new ArrayList<Word>();
			List<Word> py = new ArrayList<Word>();
			List<Word> bh = new ArrayList<Word>();
			int questionId = question.getId();
			if (!bs.isEmpty()) {
				bs.clear();
			}
			if (!jg.isEmpty()) {
				jg.clear();
			}
			if (!py.isEmpty()) {
				py.clear();
			}
			if (!bh.isEmpty()) {
				bh.clear();
			}
			if (!py_distance.isEmpty()) {
				py_distance.clear();
			}
			if (!bh_distance.isEmpty()) {
				bh_distance.clear();
			}
			if (!bh_rank.isEmpty()) {
				bh_rank.clear();
			}
			if (!py_rank.isEmpty()) {
				py_rank.clear();
			}

			String newWord = question.getNewWord();
			Word word = wordService.getWord(newWord);
			String word_word = word.getWord();
			String word_bh = word.getBh();
			String word_py = word.getPy();
			String word_bs = word.getBs();
			String word_jg = word.getJg();
			for (Word w : wordList) {
				if (w.getBs().equals(word_bs) && !(w.getWord().equals(newWord))) {
					bs.add(w);
					System.out.println("bs:" + bs.size() + "--->" + newWord
							+ "-->" + w.getWord());
				}
			}
			if (bs.size() == 0) {
				for (Word w : wordList) {
					if (w.getJg().equals(word_jg)
							&& !(w.getWord().equals(newWord))) {
						jg.add(w);
						System.out.println("---部首大小为零---");
					}
				}
			} else if (bs.size() >= 1 && bs.size() <= 4) {
				map_word.put(questionId, bs);
				System.out.println("1<=bs.size<=4");
			} else if (bs.size() > 4) {
				for (Word w : bs) {
					if (w.getJg().equals(word_jg)
							&& !(w.getWord().equals(newWord))) {
						jg.add(w);
						System.out.println("jg.sixe-->" + jg.size()
								+ "  newWord--->" + newWord + " word-->"
								+ w.getWord());
					}
				}
				System.out.println("jg大小:" + jg.size());
			}
			/**
			 * bs-->jg比较完成 下面进行bh比较
			 */
			/*
			 * if (jg.size() == 0) {
			 * 
			 * for(Word py_word:wordList){ int distance =
			 * Distance.getEditDistance(py_word.getPy(), word_py);
			 * py_distance.add(distance); }
			 * 
			 * map_word.put(questionId, bs);
			 */
			if (jg.size() >= 1 && jg.size() <= 4) {
				map_word.put(questionId, jg);
			} else if (jg.size() > 4) {
				for (Word w : jg) {

					if (!(w.getWord().equals(newWord))) {
						DistanceAndWord distanceAndWord = new DistanceAndWord();
						int distance = Distance.getEditDistance(w.getBh(),
								word_bh);
						distanceAndWord.setDistance(distance);
						distanceAndWord.setWord(w);
						bh_distance.add(distanceAndWord);
					}
				}
			}
			/**
			 * bh结束 下面比较py
			 * 
			 */

			if (bh_distance.size() >= 1 && bh_distance.size() <= 4) {
				for (DistanceAndWord daw : bh_distance) {
					temp.add(daw.getWord());
				}
				map_word.put(questionId, temp);
			} else if (bh_distance.size() > 4) {
				/*
				 * 1.排序 2.取前10个
				 */
				Collections.sort(bh_distance);
				bh_rank = bh_distance;

				for (int i = 0; i < bh_rank.size(); i++) {
					if (i <= 10) {
						DistanceAndWord daw = bh_rank.get(i);
						bh.add(daw.getWord());
					} else {
						break;
					}

				}
				for (Word w : bh) {
					if (!(w.getWord().equals(newWord))) {
						DistanceAndWord distanceAndWord = new DistanceAndWord();
						int distance = Distance.getEditDistance(w.getPy(),
								word_py);
						distanceAndWord.setDistance(distance);
						distanceAndWord.setWord(w);
						py_distance.add(distanceAndWord);
					}
				}
			}
			if (py_distance.size() >= 1 && py_distance.size() <= 4) {
				for (DistanceAndWord daw : py_distance) {
					temp.add(daw.getWord());
				}
				map_word.put(questionId, temp);
			} else if (py_distance.size() > 4) {
				/*
				 * >4的情况 1.排序取前四个
				 */
				Collections.sort(py_distance);
				py_rank = py_distance;
				for (int i = 0; i < py_rank.size(); i++) {
					if (i <= 4) {
						DistanceAndWord daw = py_rank.get(i);
						temp.add(daw.getWord());
					} else {
						break;
					}

				}
				map_word.put(questionId, temp);
				System.out.println("tmpe.size--->" + temp.size());
				System.out.println("py_distance.size--->" + py_distance.size());
				System.out.println("map_word.size--->" + map_word.size());
			}
			/*
			 * if(!temp.isEmpty()){ temp.clear(); }
			 */

		}
		mv.addObject("TEXTLIST", textDropdownList);
		mv.addObject("article", article);
		mv.addObject("questionList", questionList);
		mv.addObject("map_word", map_word);
		return mv;
	}

	@RequestMapping(value = "/scoreSubmit")
	@ResponseBody
	public Map<String, Object> scoreSubmitPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> params) {
		
		String articleId = params.get("articleId").toString();
		String newWord_Word = params.get("word").toString();
		String scoreVal = params.get("scoreVal").toString();
		String word = newWord_Word.substring(0, 1);
		String newWord = newWord_Word.substring(2, 3);
		int score = Integer.parseInt(scoreVal);
		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");
		Map<String, Object> result = new HashMap<String, Object>();
		Word newWord_zk = wordService.getWord(newWord);
		Word word_zk = wordService.getWord(word);
		int bhSimilarity = Distance.getEditDistance(newWord_zk.getBh(),
				word_zk.getBh());
		int pySimilarity = Distance.getEditDistance(newWord_zk.getPy(),
				word_zk.getPy());
		int bsSimilarity = newWord_zk.getBs().equals(word_zk.getBs()) ? 1 : 0;
		int jgSimilarity = newWord_zk.getJg().equals(word_zk.getJg()) ? 1 : 0;
		WordSimilarity ws = new WordSimilarity();
		ws.setArticleId(articleId);
		ws.setNewWord(word);
		ws.setMatchedWord(newWord);
		ws.setBhSimilarity(bhSimilarity);
		ws.setPySimilarity(pySimilarity);
		ws.setBsSimilarity(bsSimilarity);
		ws.setJgSimilarity(jgSimilarity);
		ws.setScore(score);
		ws.setUserId(user.getUserId());
		try {
			wordSimilarityService.insertWordSimilarity(ws);
			result.put("success", "提交成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("error", "error!");
		}

		return result;
	}
}
