package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.User;;



public interface UserDAO {

	/**
	 * 添加新用户
	 * 
	 * @param user
	 * @return
	 */
	public void addUser(User user);
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public void deleteUser(String userId);
	/**
	 * 更新用户资料
	 * @param user
	 * @return
	 */
	public void updateUser(User user);
	
	public User getUserById(String id);
	public List<User> getPersonList(String permission);
	
	public List<User> getUserMap(Map<String,Object> map);
	public int getCount();

}
