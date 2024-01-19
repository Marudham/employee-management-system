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

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
		if(userService.isUserExist(email)) {
			if(userService.isValidUser(email,password)) {
				if(userService.getUser(email).isAdmin()) {
					if(userService.getUser(email).isVerified()) {
						session.setAttribute("adminId", userService.getUser(email).getId());
						model.addAttribute("employees", employeeService.fetchAllEmployeeByAdminId((long)session.getAttribute("adminId")));
						return "adminHome";
					}else {
						model.addAttribute("message", "Entered Email is not verified, Check your Email or Click on Verify Email");
						model.addAttribute("verify", true);
						return "login";
					}
				}else {
					model.addAttribute("message", "Entered Email is not Approved By SUPER_ADMIN");
					return "login";
				}
			}else {
				model.addAttribute("message", "Incorrect Password");
				return "login";
			}
		}else {
			model.addAttribute("message", "Entered Email Does not exist");
			return "login";
		}
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user,Model model) {
		if(!userService.isUserExist(user.getEmail())) {
			user.setRole("Admin");
			if(userService.addUser(user) == 0) {
				model.addAttribute("message", "Registered Successfully");
				model.addAttribute("note", true);
			}else {
				model.addAttribute("message", "Some Problem Occured while Register, Please Try Again");				
			}
			return "register";
		}else {
			model.addAttribute("message", "Entered Email already Registered");
			return "register";
		}

	}

	@GetMapping("/adminInfo/{id}")
	public String adminInfo(@PathVariable long id, Model model) {
		model.addAttribute("adminInfo", userService.getUserById(id));
		return "adminInfo";
	}

	@GetMapping("/verify")
	public String verifyToken(@RequestParam String token, @RequestParam Long id) {
		if(userService.verifyToken(id,token)) {
			//
			return "login"; 
		}else {
			return "index";
		}
	}


}
