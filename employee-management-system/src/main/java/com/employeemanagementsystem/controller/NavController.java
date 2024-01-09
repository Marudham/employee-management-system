package com.employeemanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee() {
		return "addEmployee";
	}
	
	@GetMapping("/index")
	public String index(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
