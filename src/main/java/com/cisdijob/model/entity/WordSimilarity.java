package com.cisdijob.model.entity;

public class WordSimilarity {
	private int id;
	private String articleId;
	private String newWord;
	private String matchedWord;
	private int pySimilarity;
	private int bhSimilarity;
	private int bsSimilarity;
	private int jgSimilarity;
	private int score;
	private String userId;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getPySimilarity() {
		return pySimilarity;
	}
	public void setPySimilarity(int pySimilarity) {
		this.pySimilarity = pySimilarity;
	}
	public int getBhSimilarity() {
		return bhSimilarity;
	}
	public void setBhSimilarity(int bhSimilarity) {
		this.bhSimilarity = bhSimilarity;
	}
	public int getBsSimilarity() {
		return bsSimilarity;
	}
	public void setBsSimilarity(int bsSimilarity) {
		this.bsSimilarity = bsSimilarity;
	}
	public int getJgSimilarity() {
		return jgSimilarity;
	}
	public void setJgSimilarity(int jgSimilarity) {
		this.jgSimilarity = jgSimilarity;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
}
