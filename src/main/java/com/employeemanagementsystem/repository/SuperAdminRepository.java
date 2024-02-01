package com.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entities.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer>{

	SuperAdmin findByEmail(String email);

}
