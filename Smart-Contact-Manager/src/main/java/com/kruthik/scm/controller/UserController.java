package com.kruthik.scm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kruthik.scm.dtos.ContactDTO;

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

	@GetMapping("/add-contact")
	public String addContact(Model model) {
		ContactDTO contactDto = new ContactDTO();
		model.addAttribute("contactDTO", contactDto);
		return "user/contact/addContact";
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
