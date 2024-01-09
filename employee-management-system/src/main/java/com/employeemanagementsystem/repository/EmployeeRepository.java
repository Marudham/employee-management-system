package com.employeemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmail(String email);

	List<Employee> findByAddedByAdminId(long id);

}
