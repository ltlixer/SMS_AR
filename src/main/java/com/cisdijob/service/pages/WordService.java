package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Word;

public interface WordService {
	public void insertWord(Word word);
	public void updateWord(Word word);
	public Word getWord(String newWord);
	public List<Word> getWordList();
	public List<Word> getWordListByMap(Map<String,Object> map);
	public int getWordCount();
	public void deletWord();
}
