package com.cisdijob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.QuestionDAO;
import com.cisdijob.model.entity.Question;
import com.cisdijob.service.pages.QuestionService;
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionDAO questionDAO;
	public List<Question> getQuestionListByArticleId(String articleId) {
		// TODO Auto-generated method stub
		return questionDAO.getQuestionListByArticleId(articleId);
	}
	public void insertQuestion(Question question) {
		// TODO Auto-generated method stub
		if(questionDAO.getQuestion(question).isEmpty())
			questionDAO.insertQuestion(question);
	}

}
