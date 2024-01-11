package com.employeemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employeemanagementsystem.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmail(String email);

	List<Employee> findByAddedByAdminId(long id);

	@Query(value = "SELECT * FROM employee e Where e.department=:department AND e.position=:position", nativeQuery = true)
	List<Employee> findByDepartmentPosition(String department, String position);

}
