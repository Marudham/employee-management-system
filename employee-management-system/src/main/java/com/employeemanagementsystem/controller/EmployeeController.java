package com.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employeemanagementsystem.entities.Employee;
import com.employeemanagementsystem.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public String addEmployee(@ModelAttribute Employee employee,Model model,HttpSession session) {
		if(!employeeService.isEmployeeExist(employee.getEmail())) {
			employee.setAddedByAdminId((long)session.getAttribute("adminId"));
			employeeService.addEmployee(employee);
			model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
			return "adminHome";
		}else {
			return "addEmployee";
		}
	}
	
	@GetMapping("/employeeDetails/{id}")
	public String employeeDetails(@PathVariable long id,Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "employeeDetails";
	}
	
	@GetMapping("/employee/edit/{id}")
	public String employeeEdit(@PathVariable long id,Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "updateEmployee";
	}
	
	@PostMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable long id,
			@ModelAttribute Employee employee,
			Model model,HttpSession session) {
//		Employee existingEmployee = employeeService.getEmployeeById(id);
//		existingEmployee.setId(id);
//		existingEmployee.setFirstName(employee.getFirstName());
//		existingEmployee.setSecondName(employee.getSecondName());
//		existingEmployee.setEmail(employee.getEmail());
//		existingEmployee.setPhoneNo(employee.getPhoneNo());
//		existingEmployee.setAddress(employee.getAddress());
//		existingEmployee.setGender(employee.getGender());
//		existingEmployee.setJoinDate(employee.getJoinDate());
//		existingEmployee.setDepartment(employee.getDepartment());
//		existingEmployee.setPosition(employee.getPosition());
//		existingEmployee.setSalary(employee.getSalary());
//		existingEmployee.setSupervisor(employee.getSupervisor());
//		existingEmployee.setProject(employee.getProject());
//		existingEmployee.setEducation(employee.getEducation());
//		existingEmployee.setStatus(employee.getStatus());
//		employeeService.addEmployee(existingEmployee);
		employee.setAddedByAdminId((long)session.getAttribute("adminId"));
		employeeService.addEmployee(employee);
		model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
		return "adminHome";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id, Model model,HttpSession session) {
		employeeService.deleteEmployee(id);
		model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
		return "adminHome";
	}
	
	
	@GetMapping("/admin")
	public String admin(Model model,HttpSession session) {
		model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
		return "adminHome";
	}
	
}
