package com.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemanagementsystem.entities.Employee;
import com.employeemanagementsystem.entities.User;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/user")
	public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
		if(userService.isUserExist(email)) {
			if(userService.isValidUser(email,password)) {
				if(userService.getUser(email).getRole().equals("admin")) {
					session.setAttribute("adminId", userService.getUser(email).getId());
					model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
					return "adminHome";
//					model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId(userService.getUser(email).getId()));
//					return "adminHome";
				}else {
//					session.setAttribute("employeeId", employeeService.getEmployeeByEmail(email));
					Employee emp = employeeService.getEmployeeByEmail(email);
					model.addAttribute("employee",emp);
					model.addAttribute("adminInfo", userService.getUserById(emp.getAddedByAdminId()));
					return "employeeHome";
				}
			}else {
				return "login";
			}
		}else {
			return "register";
		}
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user,Model model) {
		if(user.getRole().equals("admin")) {
			if(!userService.isUserExist(user.getEmail())) {
				userService.addUser(user);
				return "index";
			}else {
				return "register";
			}
		}else {
			if(employeeService.isEmployeeExist(user.getEmail())) {
				userService.addUser(user);
				return "index";
			}else {
//				model.addAttribute("message", "Entered Email is not of an Employee");
				return "register";
			}
		}
	
	}
	
	@GetMapping("/adminInfo/{id}")
	public String adminInfo(@PathVariable long id, Model model) {
		model.addAttribute("adminInfo", userService.getUserById(id));
		return "adminInfo";
	}
	

	
	
}
