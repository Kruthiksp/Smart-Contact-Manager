package com.kruthik.scm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


	@GetMapping("/dashboard")
	public String userDashboard() {
		return "user/dashboard";
	}

	@GetMapping("/profile")
	public String userProfile(Model model, Authentication authentication) {

		return "user/profile";
	}

	@GetMapping("/contacts")
	public String userContacts() {
		return "user/contacts";
	}

	@GetMapping("/update-contact")
	public String updateContact() {
		return "";
	}

	@GetMapping("/delete-contact")
	public String deleteContact() {
		return "";
	}

	@GetMapping("/delete-account")
	public String deleteAccount() {
		return "";
	}

}
