package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.Question;

public interface QuestionDAO {
	public List<Question> getQuestionListByArticleId(String articleId);
	public void insertQuestion(Question question);
}
