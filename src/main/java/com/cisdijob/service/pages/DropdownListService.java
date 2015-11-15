package com.cisdijob.service.pages;

import java.util.List;

import com.cisdijob.model.entity.DropdownList;

public interface DropdownListService {
	public List<DropdownList> getDropdownList(String code);
	public List<DropdownList> getDropdownListTwo(String code);
}
