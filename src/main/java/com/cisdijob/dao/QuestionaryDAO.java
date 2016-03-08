package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.Questionary;

public interface QuestionaryDAO {
	public List<Questionary> getQuestionarysByUserId(String userId);
	public Questionary getQuestionarysByQuestionary(Questionary questionary);
	
	public void insertQuestionary(Questionary questionary);
	public void deleteQuestionary(Questionary questionary);
}
