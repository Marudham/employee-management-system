package com.employeemanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.entities.User;
import com.employeemanagementsystem.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public boolean isUserExist(String email) {
		if(userRepo.findByEmail(email) == null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void addUser(User user) {
		userRepo.save(user);
	}

	@Override
	public boolean isValidUser(String email, String password) {
		if(userRepo.findByEmail(email).getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User getUserById(long id) {
		return userRepo.findById(id).get();
	}

}
