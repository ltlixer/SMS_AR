package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.WordSimilarityDAO;
import com.cisdijob.model.entity.WordSimilarity;
import com.cisdijob.service.pages.WordSimilarityService;
@Service
@Transactional(rollbackFor = Exception.class)
public class WordSimilarityServiceImpl implements WordSimilarityService{
	@Autowired
	private WordSimilarityDAO wordSimilarityDAO;
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
	
	
}
