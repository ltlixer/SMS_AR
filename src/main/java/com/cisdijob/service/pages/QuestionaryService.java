package com.cisdijob.service.pages;

import java.util.List;

import com.cisdijob.model.entity.Question;
import com.cisdijob.model.entity.Questionary;

public interface QuestionaryService {
	/**
	 * 获取该用户所有打分时间
	 * @param userId 用户Id
	 * @return
	 */
	public List<Questionary> getQuestionarysByUserId(String userId);
	/**
	 * 根据提供的questionary获取某一确定打分时间
	 * @param questionary 该参数必须拥有articleId和userId这两个变量值
	 * @return
	 */
	public Questionary getQuestionarysByQuestionary(Questionary questionary);
	
	/**
	 * 插入一条打分时间记录
	 * @param questionary
	 */
	public void insertQuestionary(Questionary questionary);
	/**
	 * 删除提供的questionary对应的打分时间记录
	 * @param questionary 该参数必须拥有articleId和userId这两个变量值
	 */
	public void deleteQuestionary(Questionary questionary);
}
