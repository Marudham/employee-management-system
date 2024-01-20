package com.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemanagementsystem.entities.Employee;
import com.employeemanagementsystem.entities.EmployeeLogin;
import com.employeemanagementsystem.service.AdminService;
import com.employeemanagementsystem.service.EmailService;
import com.employeemanagementsystem.service.EmployeeLoginService;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.service.TokenService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeLoginController {

	@Autowired
	EmployeeLoginService employeeLoginService;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	AdminService adminService;

	@PostMapping("/prepareLink")
	public String prepareLink(@ModelAttribute EmployeeLogin employeeLogin, HttpSession session, Model model) {
		try {
			if(employeeService.isEmployeeExist(employeeLogin.getEmail())) {
				String token = tokenService.generateUniqueToken();
				employeeLogin.setVerificationToken(token);
				employeeLoginService.addEmployeeLogin(employeeLogin);
				String subject = "Employee Login - Employee Management System";
				String body = "To View Employee Details click the following link: "
							+ "http://localhost:8080/verifyEmployee?token=" + employeeLogin.getVerificationToken() 
			                + "&id=" + employeeLogin.getId();
				emailService.sendEmail(employeeLogin.getEmail(), subject, body);
				model.addAttribute("message", "Email has been sent, Check your Email to view Employee Details");
				return "employeeLogin";
			}else {
				model.addAttribute("message", "Entered Email is not found in Employee Database");
				return "employeeLogin";
			}
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem occured while preparing the Verification Link, Enter valid email or Try again");
			return "employeeLogin";
		}
	}
	
	@GetMapping("/verifyEmployee")
	public String verifyEmployee(@RequestParam String token, @RequestParam Long id, Model model) {
		try {
			if(employeeLoginService.verifyToken(token,id)) {
				Employee employee = employeeService.getEmployeeById(id);
				model.addAttribute("employee", employee);
				model.addAttribute("adminInfo", adminService.getAdminById(employee.getAddedByAdminId()));
				return "employeeHome";
			}else {
				model.addAttribute("message", "Cannot verify the Link, Try again");
				return "employeeLogin";
			}
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem occured while Verifying the Link, Try again");
			return "employeeLogin";
		}
	}
	
}
