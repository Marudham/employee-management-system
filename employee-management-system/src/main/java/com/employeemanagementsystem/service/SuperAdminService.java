package com.employeemanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.entities.SuperAdmin;
import com.employeemanagementsystem.repository.SuperAdminRepository;

@Service
public class SuperAdminService {

	@Autowired
	SuperAdminRepository repo;

	public SuperAdmin getSuperAdmin(String email) {
		return	repo.findByEmail(email);
	}
	
	public boolean isSuperAdminExist(String email) {
		if(repo.findByEmail(email) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isValidSuperAdmin(String email, String password) {
		if(repo.findByEmail(email).getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}
}
