package com.cisdijob.service.pages;

import java.util.List;

import com.cisdijob.model.entity.Question;

public interface QuestionService {
	public List<Question> getQuestionListByArticleId(int articleId);
	public void insertQuestion(Question question);
}
