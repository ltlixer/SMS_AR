package com.cisdijob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.DropdownListDAO;
import com.cisdijob.model.entity.DropdownList;
import com.cisdijob.service.pages.DropdownListService;
@Service
@Transactional(rollbackFor = Exception.class)
public class DropdownListServiceImpl implements DropdownListService{
	@Autowired
	private DropdownListDAO dropdownList;
	public List<DropdownList> getDropdownList(String code) {
		// TODO Auto-generated method stub
		return dropdownList.getDropdownList(code);
	}
	public List<DropdownList> getDropdownListTwo(String code) {
		// TODO Auto-generated method stub
		return dropdownList.getDropdownListTwo(code);
	}

}
