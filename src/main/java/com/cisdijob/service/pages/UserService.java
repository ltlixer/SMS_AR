package com.cisdijob.service.pages;
//gjp:2015/9/5
import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.User;
import com.cisdijob.model.entity.WordScore;

public interface UserService {
	/**
	 * 添加新用户
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deleteUser(String userId);
	/**
	 * 更新用户资料
	 * @param user
	 * @return
	 */
	public void updateUser(User user);
	
	public User getUserById(String userId);
	public Map<String,Object> login(String userName,String password);
	public List<User> getPersonList(String permission);
	
	public List<User> getUserMap(Map<String,Object> map);
	public int getCount();
	
	
}
