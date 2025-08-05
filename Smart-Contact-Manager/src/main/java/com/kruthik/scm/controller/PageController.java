package com.kruthik.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kruthik.scm.dtos.ResetPasswordDTO;
import com.kruthik.scm.dtos.UserDTO;

@Controller
public class PageController {

	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/services")
	public String services() {
		return "services";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/signup")
	public String signup(Model model) {

		UserDTO userDTO = new UserDTO();
		model.addAttribute("userDTO", userDTO);

		return "register";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/resetPassword")
	public String resetPassword(Model model) {

		model.addAttribute("resetPasswordDTO", new ResetPasswordDTO());

		return "user/reset-password";
	}

}
