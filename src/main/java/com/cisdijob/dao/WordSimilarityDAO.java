package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.WordSimilarity;

public interface WordSimilarityDAO {
	public void insertWordSimilarity(WordSimilarity wordSimilarity);
	public List<WordSimilarity> getWordSimilarity(Map<String,Object> map);
	public int getWordSimilarityCount(Map<String,Object> map);
}
