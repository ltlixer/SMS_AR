package com.cisdijob.service.pages;
//gjp:2015/9/5
import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.User;

public interface UserService {
	public User getUserById(String userId);
	public Map<String,Object> login(String userName,String password);
	public List<User> getPersonList(String permission);
}
