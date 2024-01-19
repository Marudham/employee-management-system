package com.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByIdAndEmailVerificationToken(Long id, String token);

}
