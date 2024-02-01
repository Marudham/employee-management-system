package com.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entities.ForgotPassword;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long>{

	ForgotPassword findByEmail(String email);

}
