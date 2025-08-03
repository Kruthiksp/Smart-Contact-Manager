package com.kruthik.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kruthik.scm.entities.User;
import com.kruthik.scm.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@GetMapping("/verify-email")
	public String emailVerification(@RequestParam String token, RedirectAttributes redirectAttributes) {

		User user = userService.findUserByVerificationToken(token);

		if (user != null && user.getEmailToken().equals(token)) {

			int updateEnabled = userService.updateEnable(true, true, true, token);

			if (updateEnabled > 0) {

				redirectAttributes.addFlashAttribute("toastMessage", "Email verification Successful!");
				redirectAttributes.addFlashAttribute("toastType", "success");

				return "redirect:/login";

			} else {
				redirectAttributes.addFlashAttribute("toastMessage", "Invalid verification link Failed!");
				redirectAttributes.addFlashAttribute("toastType", "error");

				return "redirect:/signup";
			}

		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Invalid verification link Failed!");
			redirectAttributes.addFlashAttribute("toastType", "error");

			return "redirect:/signup";
		}

	}

}
