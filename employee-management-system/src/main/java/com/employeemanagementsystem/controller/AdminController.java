package com.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemanagementsystem.entities.Admin;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password,Model model,HttpSession session) {
		if(adminService.isAdminExist(email)) {
			if(adminService.isValidAdmin(email,password)) {
				if(adminService.getAdmin(email).isAdmin()) {
					if(adminService.getAdmin(email).isVerified()) {
						session.setAttribute("user", adminService.getAdmin(email).getUsername());
						session.setAttribute("adminId", adminService.getAdmin(email).getId());
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
	public String register(@ModelAttribute Admin admin,Model model) {
		if(!adminService.isAdminExist(admin.getEmail())) {
			if(adminService.addAdmin(admin) == 0) {
				model.addAttribute("message", "Registered Successfully");
				model.addAttribute("note", true);
			}else {
				model.addAttribute("message", "Some Problem Occured while Register. Enter a Valid Email or Please Try Again");				
			}
			return "register";
		}else {
			model.addAttribute("message", "Entered Email already Registered");
			return "register";
		}

	}

	@GetMapping("/adminInfo/{id}")
	public String adminInfo(@PathVariable long id, Model model) {
		model.addAttribute("adminInfo", adminService.getAdminById(id));
		return "adminInfo";
	}

	@GetMapping("/verify")
	public String verifyToken(@RequestParam String token, @RequestParam Long id, Model model) {
		if(adminService.verifyToken(id,token)) {
			model.addAttribute("message", "Email Verified Sucessfully");
			return "login"; 
		}else {
			model.addAttribute("message", "Cannot verify This Email");
			return "index";
		}
	}

	@PostMapping("/requestEmail")
	public String requestEmail(@RequestParam String email, Model model) {
		try {
			if(adminService.isAdminExist(email)) {
				adminService.sendVerificationEmail(email);
				model.addAttribute("message", "Verification Email has been Sent");
				return "requestEmail";
			}else {
				model.addAttribute("message", "Cannot Find the Email, Please Enter Correct Email or Register");
				return "requestEmail";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Problem Occured While sending Email, Enter a Valid Email or try Again");
			return "requestEmail";
		}
	}

}
