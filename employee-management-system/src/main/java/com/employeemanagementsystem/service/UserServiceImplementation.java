package com.employeemanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.entities.User;
import com.employeemanagementsystem.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public boolean isUserExist(String email) {
		if(userRepo.findByEmail(email) == null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public int addUser(User user) {
		try {
			String token = tokenService.generateUniqueToken();
			user.setEmailVerificationToken(token);
			userRepo.save(user);
			emailService.sendVerificationEmail(user.getEmail(), user.getEmailVerificationToken(), user.getId());
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
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

	@Override
	public boolean verifyToken(Long id, String token) {
		User user = userRepo.findByIdAndEmailVerificationToken(id, token);
		
		if(user != null) {
			user.setVerified(true);
			userRepo.save(user);
			return true;
		}
		return false;
	}

}
