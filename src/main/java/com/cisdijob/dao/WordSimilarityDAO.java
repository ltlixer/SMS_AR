package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.WordSimilarity;

public interface WordSimilarityDAO {
	public void insertWordSimilarity(WordSimilarity wordSimilarity);
	/**
	 * 根据ID主键进行更新数据
	 * @param wordSimilarity
	 */
	public void updateWordSimilarity(WordSimilarity wordSimilarity);
	public List<WordSimilarity> getWordSimilarity(Map<String,Object> map);
	public int getCount();
	public int getWordSimilarityCount(Map<String,Object> map);
	public List<WordSimilarity> getWordSimilarityByArticleId(WordSimilarity wordSimilarity);
	public List<WordSimilarity> getWordSimilarityByWS(WordSimilarity wordSimilarity);
	public List<WordSimilarity> getWordSimilarityAll();
}
