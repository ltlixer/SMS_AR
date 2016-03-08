package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.ScoreCount;
import com.cisdijob.model.entity.WordScore;

public interface WordScoreDAO {
	public void insertWordScore(WordScore wordScore);
	public List<WordScore> getWordScore(Map<String,Object> map);
	public List<WordScore> getWordScoreList();
	public int getCount();
	
	/**
	 * 获取打分统计，用于计算特征
	 */
	public List<ScoreCount> getUserScore(String user);
	public List<ScoreCount> getRandomUserScore(String user);
	public List<ScoreCount> getUserScoreByArticle(String articleId);
	public List<ScoreCount> getUserScoreBySC(ScoreCount scoreCount);
	
	/**
	 * 通过wordScore中的userId和wordSimilaritId获取用户的打分记录
	 * @param wordScore
	 * @return
	 */
	public List<WordScore> getWordScoreByWS(WordScore wordScore);
	public void updateWordScore(WordScore wordScore);
}
