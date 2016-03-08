package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.ScoreCount;
import com.cisdijob.model.entity.WordScore;

public interface WordScoreService {
	/**
	 * 插入一条打分记录
	 * @param wordScore
	 */
	public void insertWordScore(WordScore wordScore);
	/**
	 * 获取表中全部打分记录
	 * @return WordScore的list集合
	 */
	public List<WordScore> getWordScoreList();
	/**
	 * 获取相应位置的记录，如第10-20条
	 * @param map 	map对象中需要有如下两个键值
	 * 				map.put("startNumber", 1);
	 * 				map.put("perNumber", 10);
	 * @return
	 */
	public List<WordScore> getWordScore(Map<String,Object> map);
	/**
	 * 获取打分记录总数
	 * @return 表中记录总数
	 */
	public int getCount();
	
	/**
	 * 获取打分统计，用于计算特征
	 */
	public List<ScoreCount> getUserScore(String user);
	/**
	 * 获取user1，user2，user3，user4总的打分统计
	 * @param user
	 * @return
	 */
	public List<ScoreCount> getAllUserScore();
	/**
	 * 随机产生的候选项的问题
	 * 获取user1，user2，user3，user4总的打分统计
	 * @param user
	 * @return
	 */
	public List<ScoreCount> getRandomAllUserScore();
	public List<ScoreCount> getUserScoreByArticle(String articleId);
	/**
	 * 根据userId和articleId获得打分记录
	 * @param scoreCount 必须有userId和articleId
	 * @return
	 */
	public List<ScoreCount> getUserScoreBySC(ScoreCount scoreCount);
	
	/**
	 * 判断数据库中是否有wordScore这条记录
	 * @param wordScore 用户打分记录，需要有userId、wordSimilaritId这两个属性
	 * @return wordScore在数据库存在返回true，否则返回false
	 */
	public boolean isExits(WordScore wordScore);
	/**
	 * 修改数据库中的wordScore打分记录
	 * @param wordScore
	 */
	public void updateWordScore(WordScore wordScore);
	/**
	 * 根据wordScore中的userId和wordSimilaritId获取打分记录
	 * @param wordScore 需要有userId、wordSimilaritId这两个属性
	 * @return
	 */
	public WordScore getWordScoreByWS(WordScore wordScore);
}
