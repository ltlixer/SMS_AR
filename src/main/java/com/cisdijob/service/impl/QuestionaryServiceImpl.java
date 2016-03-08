package com.cisdijob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.QuestionaryDAO;
import com.cisdijob.model.entity.Questionary;
import com.cisdijob.service.pages.QuestionaryService;
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionaryServiceImpl implements QuestionaryService{
	@Autowired
	private QuestionaryDAO questionaryDAO;

	public List<Questionary> getQuestionarysByUserId(String userId) {
		// TODO Auto-generated method stub
		return questionaryDAO.getQuestionarysByUserId(userId);
	}

	public Questionary getQuestionarysByQuestionary(
			Questionary questionary) {
		// TODO Auto-generated method stub
		return questionaryDAO.getQuestionarysByQuestionary(questionary);
	}

	public void insertQuestionary(Questionary questionary) {
		// TODO Auto-generated method stub
		questionaryDAO.insertQuestionary(questionary);
	}

	public void deleteQuestionary(Questionary questionary) {
		// TODO Auto-generated method stub
		questionaryDAO.deleteQuestionary(questionary);
	}
	
}
