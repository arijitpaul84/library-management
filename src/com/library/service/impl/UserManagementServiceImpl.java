package com.library.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.library.dto.User;
import com.library.service.UserManagementService;

public class UserManagementServiceImpl implements UserManagementService{
	
	private ConcurrentHashMap<String,User> userMap = new ConcurrentHashMap<>();
	
	public void addUser(User user) {
		userMap.put(user.getId(), user);
		System.out.println("user is added successfully");
	}
	
	public User getUser(String id) {
		return userMap.get(id);
	}

	@Override
	public List<User> searchUserByName(String name) {
		List<User> users = (List<User>) userMap.values();
		return users.stream().filter(user -> user.getName().contains(name)).
		    collect(Collectors.toList());
	}

}
