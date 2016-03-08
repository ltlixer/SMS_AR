package com.cisdijob.service.impl;
//gjp:2015/9/5
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdijob.dao.*;
import com.cisdijob.model.entity.User;
import com.cisdijob.service.pages.UserService;
import com.cisdijob.utils.HttpServletUtil;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user = userDAO.getUserById(id);
		return user;
	}
	public Map<String,Object> login(String userName, String password) {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		boolean b = false;
		String message = "";
		User user = getUserById(userName);
		if(user != null){
			//user exist
			if(user.getPassword().equals(password)){
				//login success
				 b = true;
				 result.put("success", b);
				 //存 session
				 HttpServletUtil.getInstance().getSession().setAttribute("currentUser", user);
			}else{
				//login failure
				message = "密码错误";
				result.put("message", message);
			}
		}else{
			message = "用户名不存在";
			result.put("message", message);
		}
		return result;
			
	}
	public List<User> getPersonList(String permission) {
		// TODO Auto-generated method stub
		return userDAO.getPersonList(permission);
	}
	
	public List<User> getUserMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDAO.getUserMap(map);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return userDAO.getCount();
	}
	
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user);
	}
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(userId);
	}
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);
	}

}
