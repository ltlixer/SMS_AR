package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.WordSimilarity;

public interface WordSimilarityService {
	public void insertWordSimilarity(WordSimilarity wordSimilarity);
	/**
	 * 根据id进行更新
	 * @param wordSimilarity
	 */
	public void updateWordSimilarity(WordSimilarity wordSimilarity);
	public List<WordSimilarity> getWordSimilarity();
	public List<WordSimilarity> getWordSimilarity(Map<String,Object> map);
	public int getWordSimilarityCount(Map<String,Object> map);
	/**
	 * 通过articleId和source获取
	 * @param wordSimilarity
	 * @return
	 */
	public List<WordSimilarity> getWordSimilarityByArticleId(WordSimilarity wordSimilarity);
	/**
	 * 通过articleId和source判断是否存在
	 * @param wordSimilarity
	 * @return
	 */
	public boolean isExist(WordSimilarity wordSimilarity);
	public boolean isWSExist(WordSimilarity wordSimilarity);
	public int getCount();
	
	/**
	 * 生成随机候选字
	 * @param articleId
	 */
	public void generateRandomCandidate(String articleId);
}
