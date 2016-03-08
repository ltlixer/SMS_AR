package com.cisdijob.controller.pages;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.ViewController;
import com.cisdijob.model.entity.Article;
import com.cisdijob.model.entity.DistanceAndWord;
import com.cisdijob.model.entity.DropdownList;
import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.Questionary;
import com.cisdijob.model.entity.User;
import com.cisdijob.model.entity.Word;
import com.cisdijob.model.entity.WordScore;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.ArticleService;
import com.cisdijob.service.pages.DropdownListService;
import com.cisdijob.service.pages.QuestionService;
import com.cisdijob.service.pages.QuestionaryService;
import com.cisdijob.service.pages.WordScoreService;
import com.cisdijob.service.pages.WordService;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.Distance;
import com.cisdijob.utils.HttpServletUtil;

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
	private WordScoreService wordScoreService;
	@Resource
	private WordSimilarityService wordSimilarityService;
	@Resource
	private DropdownListService dropdownListService;
	@Resource
	private QuestionaryService questionaryService;
	/**
	 * 多项选择题
	 */
	@RequestMapping(value = "/score.html")
	public ModelAndView scorePage() {
		ModelAndView mv = this.createLayoutView("factual/score.html");
		List<Article> articleList = articleService.getArticleList();
		/*List<DropdownList> textDropdownList = dorpdownListService
				.getDropdownList(DropdownListAPI.textDropdownCode);*/
		Article article = new Article();
		article.setId("1");
		article.setTitle("小松鼠找花生");
		article.setContent("大树旁边的地里种了许多花生。花生已经开花了，一朵朵金黄色的小花，在阳光下格外鲜艳。<br/>小松鼠问鼹鼠：“这是什么花呀？”鼹鼠说：“这是花生的花。到了秋天，会结花生，花生可好吃了！”小松鼠很高兴，他想：等花结了果，我就把花生摘下来，留着冬天吃。<br/>" +
				"小松鼠每天都到地里去，看看结花生了没有。<br/>他等啊，等啊，等到花都落光了，也没看见一个花生。<br/>小松鼠感到奇怪，自言自语地说：“是谁把花生摘走了呢？”");
		List<Question> questionList = questionService
				.getQuestionListByArticleId("1");
		List<DropdownList> gradeList = dropdownListService.getDropdownListTwo("PRIMARY");
		mv.addObject("GradeList",gradeList);
		mv.addObject("TEXTLIST", articleList);
		mv.addObject("article", article);
		mv.addObject("questionList", questionList);
		return mv;
	}
	/**
	 * 随机多项选择题
	 */
	@RequestMapping(value = "/random-score.html")
	public ModelAndView randomScorePage() {
		ModelAndView mv = this.createLayoutView("factual/random-score.html");
		List<Article> articleList = articleService.getArticleList();
		/*List<DropdownList> textDropdownList = dorpdownListService
				.getDropdownList(DropdownListAPI.textDropdownCode);*/
		Article article = new Article();
		article.setId("1");
		article.setTitle("小松鼠找花生");
		article.setContent("大树旁边的地里种了许多花生。花生已经开花了，一朵朵金黄色的小花，在阳光下格外鲜艳。<br/>小松鼠问鼹鼠：“这是什么花呀？”鼹鼠说：“这是花生的花。到了秋天，会结花生，花生可好吃了！”小松鼠很高兴，他想：等花结了果，我就把花生摘下来，留着冬天吃。<br/>" +
				"小松鼠每天都到地里去，看看结花生了没有。<br/>他等啊，等啊，等到花都落光了，也没看见一个花生。<br/>小松鼠感到奇怪，自言自语地说：“是谁把花生摘走了呢？”");
		List<Question> questionList = questionService
				.getQuestionListByArticleId("1");
		List<DropdownList> gradeList = dropdownListService.getDropdownListTwo("PRIMARY");
		mv.addObject("GradeList",gradeList);
		mv.addObject("TEXTLIST", articleList);
		mv.addObject("article", article);
		mv.addObject("questionList", questionList);
		return mv;
	}

	/**
	 * 事实类问题
	 * 
	 */
	@RequestMapping(value = "/factual-question.html")
	public ModelAndView factualQuestionPage() {
		ModelAndView mv = this
				.createLayoutView("factual/factual-question.html");
		return mv;
	}

	@RequestMapping(value = "/text-system.html")
	public ModelAndView textSystemPage() {
		ModelAndView mv = this.createLayoutView("factual/text-system.html");
		return mv;
	}
	
	/**
	 * 多项选择题，文章选择后，产生问题
	 * @param request
	 * @param parmas
	 * @author gjp date 2015-111-23 组合方式 bs-->jg-->py-->bh
	 */
	@RequestMapping(value = "/articleSelect")
	@ResponseBody
	public ModelAndView articleSelect(HttpServletRequest request,
			@RequestBody Map<String, Object> parmas) {
		String articleId = parmas.get("articleId").toString();
		//int id = Integer.parseInt(articleId);
		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/article-fragment");
		List<Article> articleList = articleService.getArticleList();
		/*List<DropdownList> textDropdownList = dorpdownListService
				.getDropdownList(DropdownListAPI.textDropdownCode);*/
		Article article = articleService.getArticleById(articleId);
		List<Question> questionList = questionService
				.getQuestionListByArticleId(articleId);
		List<Word> wordList = wordService.getWordList();
		List<DistanceAndWord> py_distance = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> bh_distance = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> bh_rank = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> py_rank = new ArrayList<DistanceAndWord>();
		// List<Word> map_word = new ArrayList<Word>();
		Map<Integer, List<WordScore>> map_word = new HashMap<Integer, List<WordScore>>();
		List<Question> result_question = new  ArrayList<Question>();
		
		/*
		 *产生文章中的生字时，先查询sms_wordsimilarit_t表
		 *如果表中没有生字记录就进行以下计算得出并插表
		 *然后再从表中取出记录
		 * 
		 */

		if(!wordSimilarityService.isExist(new WordSimilarity(articleId, "rule"))){
		
			for (Question question : questionList) {
				List<Word> temp = new ArrayList<Word>();
				List<Word> bs = new ArrayList<Word>();
				List<Word> jg = new ArrayList<Word>();
				List<Word> py = new ArrayList<Word>();
				List<Word> bh = new ArrayList<Word>();
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
				Word word =null;
				try {
					word = wordService.getWord(newWord.trim());
				} catch (Exception e1) {
					System.out.println("---->字库中没有\""+newWord+"\"这个字！");
				}
				
				if(word!=null){ 
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
						for(Word w : bs)
							jg.add(w);
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
					
					// bs-->jg比较完成 下面进行py比较
					if(jg.size()==0){
						for(int i=0;i<bs.size();i++){
							if(i<=4){
								Word word1 =(Word) bs.get(i);
								temp.add(word1);
							}
						}
					}else if (jg.size() >= 1 && jg.size() <= 4) {
						for(Word w : jg)
							temp.add(w);
					} else if (jg.size() > 4) {
						for (Word w : jg) {
		
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

					//py结束 下面比较bh
					if (py_distance.size() >= 1 && py_distance.size() <= 4) {
						for (DistanceAndWord daw : py_distance) {
							temp.add(daw.getWord());
						}
					} else if (py_distance.size() > 4) {
						
						//  1.排序 2.取前10个
						 
						Collections.sort(py_distance);
						py_rank = py_distance;
		
						for (int i = 0; i < py_rank.size(); i++) {
							if (i <= 10) {
								DistanceAndWord daw = py_rank.get(i);
								py.add(daw.getWord());
							} else {
								break;
							}
		
						}
						for (Word w : py) {
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
					
					if (bh_distance.size() >= 1 && bh_distance.size() <= 4) {
						for (DistanceAndWord daw : bh_distance) {
							temp.add(daw.getWord());
						}
					} else if (bh_distance.size() > 4) {
						
						// 排序取前四个
						
						Collections.sort(bh_distance);
						bh_rank = bh_distance;
						for (int i = 0; i < bh_rank.size(); i++) {
							if (i <= 4) {
								DistanceAndWord daw = bh_rank.get(i);
								temp.add(daw.getWord());
							} else {
								break;
							}
						}
						System.out.println("tmpe.size--->" + temp.size());
						System.out.println("py_distance.size--->" + py_distance.size());
					}
					
					//如果产生候选项个数小于5时，差的候选项随机产生
					Random random = new Random();
					if(temp.size()<5){
						for(int indexnum=temp.size();indexnum<5;indexnum++){
							boolean myflags = true;
							while(myflags){
								Word randomWord = wordList.get(Math.abs(random.nextInt())%(wordList.size()));
								boolean myflags1 = true;
								for(Word w : temp){
									if(w.getWord().equals(randomWord.getWord()))
										myflags1 = false;
								}
								if(myflags1){
									temp.add(randomWord);
									myflags = false;
								}
							}
						}
					}
					
					//将算出来的数据存入数据库 
					System.out.println("-----------------------------------");
					for(Word w : temp){
						WordSimilarity ws = new WordSimilarity();
						ws.setArticleId(articleId);
						ws.setNewWord(word.getWord());
						ws.setMatchedWord(w.getWord());
						ws.setBhSimilarity(Distance.getEditDistance(word.getBh(),
								w.getBh()));
						ws.setPySimilarity(Distance.getEditDistance(word.getPy(),
								w.getPy()));
						ws.setBsSimilarity(word.getBs().equals(w.getBs()) ? 1 : 0);
						ws.setJgSimilarity(word.getJg().equals(w.getJg()) ? 1 : 0);
						ws.setYySimilarity(new com.cisdijob.utils.yysimilarity.WordSimilarity().simWord(word.getWord(), w.getWord()));
						System.out.println(ws.getNewWord()+"--"+ws.getMatchedWord() +"---"+ws.getYySimilarity());
						if(!wordSimilarityService.isWSExist(ws)){
							try {
								wordSimilarityService.insertWordSimilarity(ws);
								System.out.println("存入数据库成功");
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("存入数据库失败");
							}
						}
					}
				     
					temp.clear();
				}
				
			}
			
		}
		
		System.out.println("======================================");
		//下面取出数据加入map_word中
		List<WordSimilarity> wordSimilarities = wordSimilarityService.getWordSimilarityByArticleId(new WordSimilarity(articleId, "rule"));
		for(Question q : questionList){
			List<WordScore> temp = new ArrayList<WordScore>();
			for(WordSimilarity wordSimilarity : wordSimilarities){
				if(q.getNewWord().equals(wordSimilarity.getNewWord())){
					//temp.add(wordService.getWord(wordSimilarity.getMatchedWord()));
					WordScore wordScore = new WordScore();
					wordScore.setWordSimilaritId(wordSimilarity.getId());
					wordScore.setUserId(user.getUserId());
					wordScore.setArticleId(wordSimilarity.getArticleId());
					wordScore.setMatchedWord(wordSimilarity.getMatchedWord());
					if (wordScoreService.isExits(wordScore)) {
						wordScore = wordScoreService.getWordScoreByWS(wordScore);
						System.out.println("33333333333333333333333   "+wordScore.getScore());
					}
					temp.add(wordScore);
				}
			}
			if(!temp.isEmpty()){
				result_question.add(q);
				map_word.put(q.getId(), temp);
			}
		}
		mv.addObject("TEXTLIST", articleList);
		mv.addObject("article", article);
		mv.addObject("questionList", result_question);
		mv.addObject("map_word", map_word);
		
		return mv;
	}

	/**
	 * 打分提交
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/scoreSubmit")
	@ResponseBody
	public Map<String, Object> scoreSubmitPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> params) {
		WordScore ws = new WordScore();
		Questionary questionary = new Questionary();
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String articleId = params.get("articleId").toString();
		String newWord_Word = params.get("word").toString();
		String scoreVal = params.get("scoreVal").toString();
		String sTime = params.get("startTime").toString();
		String eTime =  params.get("endTime").toString();
		Date startTime = new Date(new Long(sTime.trim()));
		Date endTime = new Date(new Long(eTime.trim()));
		String word = newWord_Word.substring(0, 1);
		String newWord = newWord_Word.substring(2, 3);
		int score = Integer.parseInt(scoreVal);
		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");
		questionary.setArticleId(articleId);
		questionary.setUserId(user.getUserId());
		questionary.setStartTime(simpleDateFormat.format(startTime));
		questionary.setEndTime(simpleDateFormat.format(endTime));
		
		ws.setUserId(user.getUserId());
		List<WordSimilarity> wsss=wordSimilarityService.getWordSimilarityByArticleId(new WordSimilarity(articleId, "rule"));
		for(WordSimilarity wss : wsss)
			if(wss.getNewWord().equals(word) && wss.getMatchedWord().equals(newWord))
				ws.setWordSimilaritId(wss.getId());
		ws.setScore(score);
		
		if(wordScoreService.isExits(ws)){
			try {
				wordScoreService.updateWordScore(ws);
				result.put("msg", "修改打分记录成功！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("msg", "修改打分记录失败!");
			}
		}else{
			try {
				wordScoreService.insertWordScore(ws);
				if(questionaryService.getQuestionarysByQuestionary(questionary)==null)
					questionaryService.insertQuestionary(questionary);
				result.put("msg", "打分提交成功！");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("msg", "打分提交失败!");
			}
		}
		return result;
	}
	
	/**
	 * 随机多项选择题，文章选择后，产生问题
	 * @param request
	 * @param parmas
	 * @author lixer date 2015-12-18
	 */
	@RequestMapping(value = "/randomArticleSelect")
	@ResponseBody
	public ModelAndView randomArticleSelect(HttpServletRequest request,
			@RequestBody Map<String, Object> parmas) {
		String articleId = parmas.get("articleId").toString();
		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragment/random-article-fragment");
		List<Article> articleList = articleService.getArticleList();
		Article article = articleService.getArticleById(articleId);
		List<Question> questionList = questionService
				.getQuestionListByArticleId(articleId);
		Map<Integer, List<WordScore>> map_word = new HashMap<Integer, List<WordScore>>();
		List<Question> result_question = new  ArrayList<Question>();
		
		
		wordSimilarityService.generateRandomCandidate(articleId);
		
		System.out.println("======================================");
		//下面取出数据加入map_word中
		List<WordSimilarity> wordSimilarities = wordSimilarityService.getWordSimilarityByArticleId(new WordSimilarity(articleId, "random"));
		for(Question q : questionList){
			List<WordScore> temp = new ArrayList<WordScore>();
			for(WordSimilarity wordSimilarity : wordSimilarities){
				if(q.getNewWord().equals(wordSimilarity.getNewWord())){
					//temp.add(wordService.getWord(wordSimilarity.getMatchedWord()));
					WordScore wordScore = new WordScore();
					wordScore.setWordSimilaritId(wordSimilarity.getId());
					wordScore.setUserId(user.getUserId());
					wordScore.setArticleId(wordSimilarity.getArticleId());
					wordScore.setMatchedWord(wordSimilarity.getMatchedWord());
					if (wordScoreService.isExits(wordScore)) {
						wordScore = wordScoreService.getWordScoreByWS(wordScore);
						System.out.println("33333333333333333333333   "+wordScore.getScore());
					}else{
						wordScore.setScore(-1);
					}
					temp.add(wordScore);
				}
			}
			if(!temp.isEmpty()){
				result_question.add(q);
				map_word.put(q.getId(), temp);
			}
		}
		mv.addObject("TEXTLIST", articleList);
		mv.addObject("article", article);
		mv.addObject("questionList", result_question);
		mv.addObject("map_word", map_word);
		
		return mv;
	}
	/**
	 * 随机多项选择题 打分提交
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/randomScoreSubmit")
	@ResponseBody
	public Map<String, Object> randomScoreSubmitPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> params) {
		WordScore ws = new WordScore();
		Questionary questionary = new Questionary();
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String articleId = params.get("articleId").toString();
		String newWord_Word = params.get("word").toString();
		String scoreVal = params.get("scoreVal").toString();
		String sTime = params.get("startTime").toString();
		String eTime =  params.get("endTime").toString();
		Date startTime = new Date(new Long(sTime.trim()));
		Date endTime = new Date(new Long(eTime.trim()));
		String word = newWord_Word.substring(0, 1);
		String newWord = newWord_Word.substring(2, 3);
		int score = Integer.parseInt(scoreVal);
		User user = (User) HttpServletUtil.getInstance().getSession()
				.getAttribute("currentUser");
		questionary.setArticleId(articleId);
		questionary.setUserId(user.getUserId());
		questionary.setStartTime(simpleDateFormat.format(startTime));
		questionary.setEndTime(simpleDateFormat.format(endTime));
		
		ws.setUserId(user.getUserId());
		List<WordSimilarity> wsss=wordSimilarityService.getWordSimilarityByArticleId(new WordSimilarity(articleId, "random"));
		for(WordSimilarity wss : wsss)
			if(wss.getNewWord().equals(word) && wss.getMatchedWord().equals(newWord))
				ws.setWordSimilaritId(wss.getId());
		ws.setScore(score);
		
		if(wordScoreService.isExits(ws)){
			try {
				wordScoreService.updateWordScore(ws);
				result.put("msg", "修改打分记录成功！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("msg", "修改打分记录失败!");
			}
		}else{
			try {
				wordScoreService.insertWordScore(ws);
				if(questionaryService.getQuestionarysByQuestionary(questionary)==null)
					questionaryService.insertQuestionary(questionary);
				result.put("msg", "打分提交成功！");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("msg", "打分提交失败!");
			}
		}
		return result;
	}

	
	@RequestMapping(value = "/submitQuestion")
	@ResponseBody
	public Map<String, Object> factualSubmitQuestion(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Object params[]) {
		/*Object dataArray[] =(Object[]) params.get("dataSets");
		for(int i=0;i<dataArray.length;i++){
			Map<String,Object> map=(Map<String,Object>)dataArray[i];
			String sentence = map.get("sentence").toString();
			System.out.println("sentence="+sentence);
		}
		*/
		Map<String,Object> result = new HashMap<String, Object>();

		return result;
	}
}
