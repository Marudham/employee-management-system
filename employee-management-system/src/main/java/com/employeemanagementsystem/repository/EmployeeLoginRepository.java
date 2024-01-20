package com.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entities.EmployeeLogin;

public interface EmployeeLoginRepository extends JpaRepository<EmployeeLogin, Long>{

	EmployeeLogin findByEmail(String email);

}
