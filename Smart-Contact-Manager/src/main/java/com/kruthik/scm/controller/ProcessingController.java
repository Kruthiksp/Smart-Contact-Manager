package com.kruthik.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kruthik.scm.entities.User;
import com.kruthik.scm.forms.UserForm;
import com.kruthik.scm.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProcessingController {

	private final UserService userService;

	@PostMapping("/do-register")
	public String registration(@ModelAttribute UserForm userForm) {

		User user = User.builder()
						.name(userForm.getName())
						.email(userForm.getEmail())
						.phoneNumber(userForm.getPhone())
						.password(userForm.getPassword())
					.build();

		User saveUser = userService.saveUser(user);

		System.out.println(saveUser);

		return "redirect:/signup";
	}
}
