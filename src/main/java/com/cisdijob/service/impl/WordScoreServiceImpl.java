package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.config.FilePathConfig;
import com.cisdijob.dao.WordDAO;
import com.cisdijob.dao.WordScoreDAO;
import com.cisdijob.model.entity.ScoreCount;
import com.cisdijob.model.entity.WordScore;
import com.cisdijob.service.pages.WordScoreService;
@Service
@Transactional(rollbackFor = Exception.class)
public class WordScoreServiceImpl implements WordScoreService {
	@Autowired
	private WordScoreDAO wordScoreDAO;
	@Autowired
	private WordDAO wordDAO;
	
	public void insertWordScore(WordScore wordScore) {
		// TODO Auto-generated method stub
		wordScoreDAO.insertWordScore(wordScore);
	}

	public List<WordScore> getWordScoreList() {
		// TODO Auto-generated method stub
		return wordScoreDAO.getWordScoreList();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return wordScoreDAO.getCount();
	}

	public List<WordScore> getWordScore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return wordScoreDAO.getWordScore(map);
	}

	public List<ScoreCount> getUserScore(String user) {
		// TODO Auto-generated method stub
		return wordScoreDAO.getUserScore(user);
	}

	public List<ScoreCount> getUserScoreByArticle(String articleId) {
		// TODO Auto-generated method stub
		return wordScoreDAO.getUserScoreByArticle(articleId);
	}

	public List<ScoreCount> getUserScoreBySC(ScoreCount scoreCount) {
		// TODO Auto-generated method stub
		return wordScoreDAO.getUserScoreBySC(scoreCount);
	}
	
	public boolean isExits(WordScore wordScore) {
		// TODO Auto-generated method stub
		if(wordScoreDAO.getWordScoreByWS(wordScore).isEmpty())
			return false;
		else
			return true;
	}

	public void updateWordScore(WordScore wordScore) {
		// TODO Auto-generated method stub
		wordScoreDAO.updateWordScore(wordScore);
	}

	public WordScore getWordScoreByWS(WordScore wordScore) {
		// TODO Auto-generated method stub
		if(!wordScoreDAO.getWordScoreByWS(wordScore).isEmpty())
			return wordScoreDAO.getWordScoreByWS(wordScore).get(0);
		else
			return null;
	}

	public List<ScoreCount> getAllUserScore() {
		// TODO Auto-generated method stub
		List<ScoreCount> scoreCounts_user = wordScoreDAO.getUserScore(FilePathConfig.scoreOfUser1);
		List<ScoreCount> scoreCounts_user1 = wordScoreDAO.getUserScore(FilePathConfig.scoreOfUser2);
		List<ScoreCount> scoreCounts_user2 = wordScoreDAO.getUserScore(FilePathConfig.scoreOfUser3);
		List<ScoreCount> scoreCounts_user3 = wordScoreDAO.getUserScore(FilePathConfig.scoreOfUser4);
		for(ScoreCount scoreCount1 : scoreCounts_user){
			for(ScoreCount scoreCount2 : scoreCounts_user1){
				if(scoreCount1.getQid().equals(scoreCount2.getQid())){
					scoreCount1.setScore_user2(scoreCount2.getScore_user1());
				}
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user){
			for(ScoreCount scoreCount2 : scoreCounts_user2){
				if(scoreCount1.getQid().equals(scoreCount2.getQid())){
					scoreCount1.setScore_user3(scoreCount2.getScore_user1());
				}
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user){
			for(ScoreCount scoreCount2 : scoreCounts_user3){
				if(scoreCount1.getQid().equals(scoreCount2.getQid())){
					scoreCount1.setScore_user4(scoreCount2.getScore_user1());
				}
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user1){
			boolean myflag=true;
			for(ScoreCount scoreCount : scoreCounts_user){
				if(scoreCount.getQid().equals(scoreCount1.getQid()))
					myflag=false;
			}
			if(myflag){
				ScoreCount temp = scoreCount1;
				temp.setScore_user2(scoreCount1.getScore_user1());
				temp.setScore_user1(0);
				scoreCounts_user.add(temp);
				
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user2){
			boolean myflag=true;
			for(ScoreCount scoreCount : scoreCounts_user){
				if(scoreCount.getQid().equals(scoreCount1.getQid()))
					myflag=false;
			}
			if(myflag){
				ScoreCount temp = scoreCount1;
				temp.setScore_user3(scoreCount1.getScore_user1());
				temp.setScore_user1(0);
				scoreCounts_user.add(temp);
				
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user3){
			boolean myflag=true;
			for(ScoreCount scoreCount : scoreCounts_user){
				if(scoreCount.getQid().equals(scoreCount1.getQid()))
					myflag=false;
			}
			if(myflag){
				ScoreCount temp = scoreCount1;
				temp.setScore_user4(scoreCount1.getScore_user1());
				temp.setScore_user1(0);
				scoreCounts_user.add(temp);
				
			}
		}
		return scoreCounts_user;
	}
	public List<ScoreCount> getRandomAllUserScore() {
		// TODO Auto-generated method stub
		List<ScoreCount> scoreCounts_user = wordScoreDAO.getRandomUserScore(FilePathConfig.scoreOfUser1);
		List<ScoreCount> scoreCounts_user1 = wordScoreDAO.getRandomUserScore(FilePathConfig.scoreOfUser2);
		List<ScoreCount> scoreCounts_user2 = wordScoreDAO.getRandomUserScore(FilePathConfig.scoreOfUser3);
		List<ScoreCount> scoreCounts_user3 = wordScoreDAO.getRandomUserScore(FilePathConfig.scoreOfUser4);
		for(ScoreCount scoreCount1 : scoreCounts_user){
			for(ScoreCount scoreCount2 : scoreCounts_user1){
				if(scoreCount1.getQid().equals(scoreCount2.getQid())){
					scoreCount1.setScore_user2(scoreCount2.getScore_user1());
				}
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user){
			for(ScoreCount scoreCount2 : scoreCounts_user2){
				if(scoreCount1.getQid().equals(scoreCount2.getQid())){
					scoreCount1.setScore_user3(scoreCount2.getScore_user1());
				}
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user){
			for(ScoreCount scoreCount2 : scoreCounts_user3){
				if(scoreCount1.getQid().equals(scoreCount2.getQid())){
					scoreCount1.setScore_user4(scoreCount2.getScore_user1());
				}
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user1){
			boolean myflag=true;
			for(ScoreCount scoreCount : scoreCounts_user){
				if(scoreCount.getQid().equals(scoreCount1.getQid()))
					myflag=false;
			}
			if(myflag){
				ScoreCount temp = scoreCount1;
				temp.setScore_user2(scoreCount1.getScore_user1());
				temp.setScore_user1(0);
				scoreCounts_user.add(temp);
				
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user2){
			boolean myflag=true;
			for(ScoreCount scoreCount : scoreCounts_user){
				if(scoreCount.getQid().equals(scoreCount1.getQid()))
					myflag=false;
			}
			if(myflag){
				ScoreCount temp = scoreCount1;
				temp.setScore_user3(scoreCount1.getScore_user1());
				temp.setScore_user1(0);
				scoreCounts_user.add(temp);
				
			}
		}
		for(ScoreCount scoreCount1 : scoreCounts_user3){
			boolean myflag=true;
			for(ScoreCount scoreCount : scoreCounts_user){
				if(scoreCount.getQid().equals(scoreCount1.getQid()))
					myflag=false;
			}
			if(myflag){
				ScoreCount temp = scoreCount1;
				temp.setScore_user4(scoreCount1.getScore_user1());
				temp.setScore_user1(0);
				scoreCounts_user.add(temp);
				
			}
		}
		return scoreCounts_user;
	}


}
