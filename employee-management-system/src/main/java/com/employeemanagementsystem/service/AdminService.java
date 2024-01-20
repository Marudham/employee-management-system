package com.employeemanagementsystem.service;

import java.util.List;

import com.employeemanagementsystem.entities.Admin;

public interface AdminService {

	boolean isAdminExist(String email);

	int addAdmin(Admin admin);

	boolean isValidAdmin(String email, String password);

	Admin getAdmin(String email);

	Admin getAdminById(long id);

	boolean verifyToken(Long id, String token);

	List<Admin> getAllAdmins();
	
	void updateAdmin(Admin admin);
	
	void deleteAdmin(Long id);

	void sendVerificationEmail(String email);
}
