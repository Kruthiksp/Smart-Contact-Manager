package com.kruthik.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/home")
	public String home(Model model) {
		System.out.println("Home Page Handller");
		model.addAttribute("name", "kruthik");
		model.addAttribute("job", "Java-Dev");
		return "home.html";
	}

	@GetMapping("/about")
	public String about() {
		System.out.println("About Page Handller");
		return "about";
	}

	@GetMapping("/services")
	public String services() {
		System.out.println("Services Page Handller");
		return "services";
	}
	
	@GetMapping("/contact")
	public String contact() {
		System.out.println("Contact Page Handller");
		return "contact";
	}

}
