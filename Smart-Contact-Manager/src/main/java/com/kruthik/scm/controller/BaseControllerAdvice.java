package com.kruthik.scm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kruthik.scm.entities.User;
import com.kruthik.scm.services.UserService;
import com.kruthik.scm.util.UserHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class BaseControllerAdvice {

	private final UserService userService;

	@ModelAttribute
	public void addLoggedInUser(Model model, Authentication authentication) {

		if (authentication == null) {
			return;
		}

		String emailOfLoggedInUser = UserHelper.getEmailOfLoggedInUser(authentication);

		User userByEmail = userService.findUserByEmail(emailOfLoggedInUser);

		if (userByEmail != null) {
			model.addAttribute("loggedInUser", userByEmail);
		} else {
			model.addAttribute("loggedInUser", null);
		}

	}

}
