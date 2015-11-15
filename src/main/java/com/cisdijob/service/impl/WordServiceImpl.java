package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.WordDAO;
import com.cisdijob.model.entity.Word;
import com.cisdijob.service.pages.WordService;
@Service
@Transactional(rollbackFor = Exception.class)
public class WordServiceImpl implements WordService{
	@Autowired
	private WordDAO wordDAO;
	public void insertWord(Word word) {
		// TODO Auto-generated method stub
		wordDAO.insertWord(word);
	}
	public Word getWord(String newWord) {
		// TODO Auto-generated method stub
		return wordDAO.getWord(newWord);
	}
	public List<Word> getWordList() {
		// TODO Auto-generated method stub
		return wordDAO.getWordList();
	}
	public List<Word> getWordListByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return wordDAO.getWordListByMap(map);
	}
	public int getWordCount() {
		// TODO Auto-generated method stub
		return wordDAO.getWordCount();
	}
	public void deletWord() {
		// TODO Auto-generated method stub
		wordDAO.deletWord();
	}

}
