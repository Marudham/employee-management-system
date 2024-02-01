package com.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entities.Admin;


public interface AdminRepository  extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);

	Admin findByIdAndEmailVerificationToken(Long id, String token);

}
