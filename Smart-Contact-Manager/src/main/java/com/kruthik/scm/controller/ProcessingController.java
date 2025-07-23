package com.kruthik.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;
import com.kruthik.scm.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProcessingController {

	private final UserService userService;

	@PostMapping("/do-register")
	public String registration(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "register";
		}

		User saveUser = userService.saveUser(userDTO);

		if (saveUser != null) {
			redirectAttributes.addFlashAttribute("toastMessage", "Registration Successful!");
			redirectAttributes.addFlashAttribute("toastType", "success");
		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Registration Failed!");
			redirectAttributes.addFlashAttribute("toastType", "error");
		}

		return "redirect:/signup";
	}
}
