package com.employeemanagementsystem.service;

import com.employeemanagementsystem.entities.ForgotPassword;

public interface ForgotPasswordService {

	void save(ForgotPassword forgotPassword);

	boolean verifyToken(String token, Long id);

	ForgotPassword getById(Long id);

	ForgotPassword getByEmail(String email);

	
}
