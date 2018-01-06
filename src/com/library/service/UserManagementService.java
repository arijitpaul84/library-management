package com.library.service;

import java.util.List;

import com.library.dto.User;

public interface UserManagementService {
	
	void addUser(User user);
	
	List<User> searchUserByName(String name);
	
	User getUser(String id);

}
