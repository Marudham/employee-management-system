package com.employeemanagementsystem.service;

import com.employeemanagementsystem.entities.User;

public interface UserService {

	boolean isUserExist(String email);

	int addUser(User user);

	boolean isValidUser(String email, String password);

	User getUser(String email);

	User getUserById(long id);

	boolean verifyToken(Long id, String token);

}
