package com.employeemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.entities.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public boolean isEmployeeExist(String email) {
		if(employeeRepo.findByEmail(email) == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<Employee> fetchAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);	
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepo.findById(id).get();
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeRepo.findByEmail(email);
	}

	@Override
	public List<Employee> fetchAllEmployeeByAdminId(long id) {
		
		return employeeRepo.findByAddedByAdminId(id);
	}

	@Override
	public List<Employee> filterEmployees(String department, String position) {
		return employeeRepo.findByDepartmentPosition(department,position);
	}

}
