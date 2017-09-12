package com.zwy.application.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwy.application.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserController {

	public static ConcurrentHashMap<Long, User> userMap = new  ConcurrentHashMap<>();
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<User> userList() {
		List<User> userList = new ArrayList<>(userMap.values());
		return userList;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		userMap.put(user.getId(), user);
		return "success";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return userMap.get(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u = userMap.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		userMap.put(id, u);
		return "success";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		userMap.remove(id);
		return "success";
	}
}
