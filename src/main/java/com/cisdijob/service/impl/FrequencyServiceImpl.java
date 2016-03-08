package com.cisdijob.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.FrequencyDAO;
import com.cisdijob.model.entity.Frequency;
import com.cisdijob.service.pages.FrequencyService;
@Service
@Transactional(rollbackFor = Exception.class)
public class FrequencyServiceImpl implements FrequencyService {
	@Autowired
	private FrequencyDAO frequencyDAO;

	public Frequency getFrequencyByWord(String word) {
		// TODO Auto-generated method stub
		return frequencyDAO.getFrequencyByWord(word);
	}
	

}
