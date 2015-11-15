package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.DropdownList;

public interface DropdownListDAO {
	public List<DropdownList> getDropdownList(String code);
	public List<DropdownList> getDropdownListTwo(String searchCode);
	
}
