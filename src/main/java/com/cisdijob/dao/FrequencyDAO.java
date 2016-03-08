package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.Frequency;

public interface FrequencyDAO {
	public Frequency getFrequencyByWord(String word);
	public List<Frequency> getFrequencyByGrade(String grade);
}
