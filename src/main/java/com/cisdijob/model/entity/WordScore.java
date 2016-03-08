package com.cisdijob.model.entity;

public class WordScore {
	private int id;
	private String userId;
	private int wordSimilaritId;
	private String articleId;
	private String newWord;
	private String matchedWord;
	private int score;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getNewWord() {
		return newWord;
	}
	public void setNewWord(String newWord) {
		this.newWord = newWord;
	}
	public String getMatchedWord() {
		return matchedWord;
	}
	public void setMatchedWord(String matchedWord) {
		this.matchedWord = matchedWord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getWordSimilaritId() {
		return wordSimilaritId;
	}
	public void setWordSimilaritId(int wordSimilaritId) {
		this.wordSimilaritId = wordSimilaritId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
