package com.kruthik.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kruthik.scm.forms.UserForm;

@Controller
public class PageController {

	@GetMapping("/home")
	public String home() {

		return "home.html";
	}

	@GetMapping("/about")
	public String about() {
		System.out.println("About Page Handller");
		return "about.html";
	}

	@GetMapping("/services")
	public String services() {
		System.out.println("Services Page Handller");
		return "services.html";
	}

	@GetMapping("/contact")
	public String contact() {
		System.out.println("Contact Page Handller");
		return "contact.html";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		
		UserForm user = new UserForm();
		model.addAttribute("userForm", user);

		return "register.html";
	}

	@GetMapping("/login")
	public String login() {
		System.out.println("Login Page Handller");
		return "login.html";
	}

}
