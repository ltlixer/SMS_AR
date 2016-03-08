package com.cisdijob.model.entity;

public class ScoreCount {
	private String qid;
	private String articleId;
	private String userId;
	private String newWord;
	private int newWordFrequency;
	private String matchedWord;
	private int matchedWordFrequency;
	private int score_user1;
	private int score_user2;
	private int score_user3;
	private int score_user4;
	private int pySimilarity;
	private int bhSimilarity;
	private int bsSimilarity;
	private int jgSimilarity;
	private double yySimilarity;
	private double yyFeatures;
	private String source;
	public ScoreCount(){
		super();
	}
	public ScoreCount(String qid, String articleId, String userId, String newWord, String matchedWord, int score_user1,
			int score_user2, int score_user3, int score_user4, int pySimilarity, int bhSimilarity, int bsSimilarity,
			int jgSimilarity, double yySimilarity) {
		super();
		this.qid = qid;
		this.articleId = articleId;
		this.userId = userId;
		this.newWord = newWord;
		this.matchedWord = matchedWord;
		this.score_user1 = score_user1;
		this.score_user2 = score_user2;
		this.score_user3 = score_user3;
		this.score_user4 = score_user4;
		this.pySimilarity = pySimilarity;
		this.bhSimilarity = bhSimilarity;
		this.bsSimilarity = bsSimilarity;
		this.jgSimilarity = jgSimilarity;
		this.yySimilarity = yySimilarity;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getYyFeatures() {
		return yyFeatures;
	}
	public void setYyFeatures(double yyFeatures) {
		this.yyFeatures = yyFeatures;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getScore_user1() {
		return score_user1;
	}
	public void setScore_user1(int score_user1) {
		this.score_user1 = score_user1;
	}
	public int getScore_user2() {
		return score_user2;
	}
	public void setScore_user2(int score_user2) {
		this.score_user2 = score_user2;
	}
	public int getScore_user3() {
		return score_user3;
	}
	public void setScore_user3(int score_user3) {
		this.score_user3 = score_user3;
	}
	public int getScore_user4() {
		return score_user4;
	}
	public void setScore_user4(int score_user4) {
		this.score_user4 = score_user4;
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
	public double getYySimilarity() {
		return yySimilarity;
	}
	public void setYySimilarity(double yySimilarity) {
		this.yySimilarity = yySimilarity;
	}
	@Override
	public String toString() {
		return "ScoreCount [qid=" + qid + ", articleId=" + articleId + ", newWord=" + newWord + ", matchedWord="
				+ matchedWord + ", score_user1=" + score_user1 + ", score_user2=" + score_user2 + ", score_user3="
				+ score_user3 + ", score_user4=" + score_user4 + ", pySimilarity=" + pySimilarity + ", bhSimilarity="
				+ bhSimilarity + ", bsSimilarity=" + bsSimilarity + ", jgSimilarity=" + jgSimilarity + ", yySimilarity="
				+ yySimilarity + "]";
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
	
	
	
}
