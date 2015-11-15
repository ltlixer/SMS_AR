package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.User;;



public interface UserDAO {

	/**
	 * 添加新用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	public User getUserById(String id);
	public int updateUser(User user);
	public int deleteUser(String userId);
	public List<User> getPersonList(String permission);

}
