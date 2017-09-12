package com.zwy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwy.entity.User;
import com.zwy.mappers.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserByUserName(String username) {
		return userMapper.getUserByUserName(username);
	}
	
	public User getUserById(int userId) {
		return userMapper.getUserById(userId);
	}
}
