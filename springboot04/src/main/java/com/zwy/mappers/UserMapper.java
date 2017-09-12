package com.zwy.mappers;

import com.zwy.entity.User;

public interface UserMapper {

	public User getUserByUserName(String username);
	
	public User getUserById(int userId);
}
