package com.employeemanagementsystem.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemanagementsystem.entities.Employee;
import com.employeemanagementsystem.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public String addEmployee(@ModelAttribute Employee employee,Model model,HttpSession session) {
		try {
			if(session.getAttribute("user") != null) {
				if(!employeeService.isEmployeeExist(employee.getEmail())) {
					if(!employeeService.isPhoneNoExist(employee.getPhoneNo())) {
						LocalDate currentDate = LocalDate.now();
						LocalDate dob = LocalDate.parse(employee.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
						if(dob.isAfter(currentDate) || dob.plusYears(18).isAfter(currentDate)) {
							model.addAttribute("message", "Enter the Valid Date of Birth");
							return "addEmployee";
						}
						employee.setAddedByAdminId((long)session.getAttribute("adminId"));
						employeeService.addEmployee(employee);
						model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
						model.addAttribute("message", "Employee added successfully");
						return "adminHome";
					}else {
						model.addAttribute("message", "Entered Employee Phone No already exist");
						return "addEmployee";
					}
				}else {
					model.addAttribute("message", "Entered Employee Email already exist");
					return "addEmployee";
				}
			}else {
				return "login";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem occured while adding employee, Try again");
			return "adminHome";
		}
	}
	
	@GetMapping("/employeeDetails/{id}")
	public String employeeDetails(@PathVariable long id,Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("employee", employeeService.getEmployeeById(id));
			return "employeeDetails";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/employee/edit/{id}")
	public String employeeEdit(@PathVariable long id,Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("employee", employeeService.getEmployeeById(id));
			return "updateEmployee";
		}else {
			return "login";
		}
	}
	
	@PostMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable long id,
			@ModelAttribute Employee employee,
			Model model,HttpSession session) {
		try {
			employee.setAddedByAdminId((long)session.getAttribute("adminId"));
			employeeService.addEmployee(employee);
			model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
			model.addAttribute("message", "Employee Updated Successfully");
			return "adminHome";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem occured while updating Employee, Try again");
			return "addEmployee";
		}
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id, Model model,HttpSession session) {
		employeeService.deleteEmployee(id);
		model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
		return "adminHome";
	}
	
	
	@GetMapping("/admin")
	public String admin(Model model,HttpSession session) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
			return "adminHome";
		}else {
			return "login";
		}
	}
	

	@GetMapping("/filter")
	public String filter(Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("employees",employeeService.fetchAllEmployee());
			return "filter";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/applyFilter")
	public String applyFilter(@RequestParam("filterBasedOn") String filterBasedOn, 
						@RequestParam("filterValue") String filterValue, 
						Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("employees",employeeService.fetchAllEmployee());
			model.addAttribute("filterEmployees", employeeService.filterEmployees(filterBasedOn, filterValue));
			return "filter";
		}else {
			return "login";
		}
	}
	
	
}
