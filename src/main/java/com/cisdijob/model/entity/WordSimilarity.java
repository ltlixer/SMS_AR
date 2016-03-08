package com.cisdijob.model.entity;

public class WordSimilarity {
	private int id;
	private String articleId;
	private String newWord;
	private int newWordFrequency;
	private String matchedWord;
	private int matchedWordFrequency;
	private int pySimilarity;
	private int bhSimilarity;
	private int bsSimilarity;
	private int jgSimilarity;
	private double yySimilarity;
	private double yyFeatures;
	private String source = "rule";
	
	public WordSimilarity() {
		super();
	}
	public WordSimilarity(String articleId, String source) {
		super();
		this.articleId = articleId;
		this.source = source;
	}
	public double getYyFeatures() {
		return yyFeatures;
	}
	public void setYyFeatures(double yyFeatures) {
		this.yyFeatures = yyFeatures;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getYySimilarity() {
		return yySimilarity;
	}
	public void setYySimilarity(double yySimilarity) {
		this.yySimilarity = yySimilarity;
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
	public int getNewWordFrequency() {
		return newWordFrequency;
	}
	public void setNewWordFrequency(int newWordFrequency) {
		this.newWordFrequency = newWordFrequency;
	}
	public int getMatchedWordFrequency() {
		return matchedWordFrequency;
	}
	public void setMatchedWordFrequency(int matchedWordFrequency) {
		this.matchedWordFrequency = matchedWordFrequency;
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
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "WordSimilarity [id=" + id + ", articleId=" + articleId
				+ ", newWord=" + newWord + ", newWordFrequency="
				+ newWordFrequency + ", matchedWord=" + matchedWord
				+ ", matchedWordFrequency=" + matchedWordFrequency
				+ ", pySimilarity=" + pySimilarity + ", bhSimilarity="
				+ bhSimilarity + ", bsSimilarity=" + bsSimilarity
				+ ", jgSimilarity=" + jgSimilarity + ", yySimilarity="
				+ yySimilarity + ", yyFeatures=" + yyFeatures + ", source="
				+ source + "]";
	}
	
	
}
