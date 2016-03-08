package com.cisdijob.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.ArticleDAO;
import com.cisdijob.dao.FrequencyDAO;
import com.cisdijob.dao.QuestionDAO;
import com.cisdijob.dao.WordDAO;
import com.cisdijob.dao.WordSimilarityDAO;
import com.cisdijob.model.entity.Article;
import com.cisdijob.model.entity.DistanceAndWord;
import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.Word;
import com.cisdijob.model.entity.WordScore;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.WordSimilarityService;
import com.cisdijob.utils.Distance;
@Service
@Transactional(rollbackFor = Exception.class)
public class WordSimilarityServiceImpl implements WordSimilarityService{
	@Autowired
	private WordSimilarityDAO wordSimilarityDAO;
	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private WordDAO wordDAO;
	@Autowired
	private FrequencyDAO frequencyDAO;
	public void insertWordSimilarity(WordSimilarity wordSimilarity) {
		// TODO Auto-generated method stub
		wordSimilarityDAO.insertWordSimilarity(wordSimilarity);
	}
	public List<WordSimilarity> getWordSimilarity(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return wordSimilarityDAO.getWordSimilarity(map);
	}
	public int getWordSimilarityCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return wordSimilarityDAO.getWordSimilarityCount(map);
	}
	public boolean isExist(WordSimilarity wordSimilarity) {
		// TODO Auto-generated method stub
		return !wordSimilarityDAO.getWordSimilarityByArticleId(wordSimilarity).isEmpty();
	}
	public boolean isWSExist(WordSimilarity wordSimilarity) {
		// TODO Auto-generated method stub
		return !wordSimilarityDAO.getWordSimilarityByWS(wordSimilarity).isEmpty();
	}
	public List<WordSimilarity> getWordSimilarityByArticleId(WordSimilarity wordSimilarity) {
		// TODO Auto-generated method stub
		return wordSimilarityDAO.getWordSimilarityByArticleId(wordSimilarity);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return wordSimilarityDAO.getCount();
	}
	public void updateWordSimilarity(WordSimilarity wordSimilarity) {
		// TODO Auto-generated method stub
		wordSimilarityDAO.updateWordSimilarity(wordSimilarity);
	}
	public List<WordSimilarity> getWordSimilarity() {
		// TODO Auto-generated method stub
		return wordSimilarityDAO.getWordSimilarityAll();
	}
	public void generateRandomCandidate(String articleId) {
		// TODO Auto-generated method stub
		Article article = articleDAO.getArticleById(articleId);
		List<Question> questionList = questionDAO
				.getQuestionListByArticleId(articleId);
		List<Word> wordList = wordDAO.getWordList();
		List<DistanceAndWord> py_distance = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> bh_distance = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> bh_rank = new ArrayList<DistanceAndWord>();
		List<DistanceAndWord> py_rank = new ArrayList<DistanceAndWord>();
		Map<Integer, List<WordScore>> map_word = new HashMap<Integer, List<WordScore>>();
		List<Question> result_question = new  ArrayList<Question>();
		
		/*
		 *产生文章中的生字时，先查询sms_wordsimilarit_t表
		 *如果表中没有生字记录就进行以下计算得出并插表
		 *然后再从表中取出记录
		 * 
		 */
		
		if(!isExist(new WordSimilarity(articleId, "random"))){
		
			for (Question question : questionList) {
				List<Word> temp = new ArrayList<Word>();
			
				String newWord = question.getNewWord();
				Word word =null;
				try {
					word = wordDAO.getWord(newWord.trim());
				} catch (Exception e1) {
					System.out.println("---->字库中没有\""+newWord+"\"这个字！");
				}
				if(word!=null){
					String frequencyGrade = getFrequencyGrade(frequencyDAO.getFrequencyByWord(word.getWord()).getFrequencyId());
					List<Word> gradeWordList= wordDAO.getWordListByFrequency(frequencyGrade);
					//候选项随机产生
					Random random = new Random();
					for(int indexnum=0;indexnum<5;indexnum++){
						boolean myflags = true;
						while(myflags){
							Word randomWord = gradeWordList.get(Math.abs(random.nextInt())%(gradeWordList.size()));
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
						
					//将数据存入数据库 
					System.out.println("-----------------------------------");
					for(Word w : temp){
						WordSimilarity ws = new WordSimilarity();
						ws.setArticleId(articleId);
						ws.setNewWord(word.getWord());
						ws.setNewWordFrequency(frequencyDAO.getFrequencyByWord(word.getWord()).getFrequencyId());
						ws.setMatchedWord(w.getWord());
						ws.setMatchedWordFrequency(frequencyDAO.getFrequencyByWord(w.getWord()).getFrequencyId());
						ws.setBhSimilarity(Distance.getEditDistance(word.getBh(),
								w.getBh()));
						ws.setPySimilarity(Distance.getEditDistance(word.getPy(),
								w.getPy()));
						ws.setBsSimilarity(word.getBs().equals(w.getBs()) ? 1 : 0);
						ws.setJgSimilarity(word.getJg().equals(w.getJg()) ? 1 : 0);
						ws.setYySimilarity(new com.cisdijob.utils.yysimilarity.WordSimilarity().simWord(word.getWord(), w.getWord()));
						double yyFeatures = 0;
						char[] questionScentence = question.getQuestion().replaceAll("[\\pP\\p{Punct}]", "").toCharArray();
						for(int i=0;i<questionScentence.length;i++){
							if(new String(questionScentence,i,1).equals(word.getWord())){
								if(i>0&&i<questionScentence.length-1){
									double aaa1 = new com.cisdijob.utils.yysimilarity.WordSimilarity().simWord(word.getWord(), new String(questionScentence,i-1,1));
									double aaa2 = new com.cisdijob.utils.yysimilarity.WordSimilarity().simWord(word.getWord(), new String(questionScentence,i+1,1));
									yyFeatures = (aaa1+aaa2+ws.getYySimilarity())/3;
								}else if(i==0){
									double aaa2 = new com.cisdijob.utils.yysimilarity.WordSimilarity().simWord(word.getWord(), new String(questionScentence,i+1,1));
									yyFeatures = (aaa2+ws.getYySimilarity())/2;
								}else if(i==questionScentence.length-1){
									double aaa1 = new com.cisdijob.utils.yysimilarity.WordSimilarity().simWord(word.getWord(), new String(questionScentence,i-1,1));
									yyFeatures = (aaa1+ws.getYySimilarity())/2;
								}else{
									yyFeatures = ws.getYySimilarity();
								}
							}
						}
						ws.setYyFeatures(yyFeatures);
						ws.setSource("random");
						System.out.println(ws.getNewWord()+"--"+ws.getMatchedWord() +"---"+ws.getYySimilarity());
						if(!isWSExist(ws)){
							try {
								wordSimilarityDAO.insertWordSimilarity(ws);
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
