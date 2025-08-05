package com.kruthik.scm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.dtos.ResetPasswordDTO;
import com.kruthik.scm.dtos.SuggestionDTO;
import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.entities.Suggestion;
import com.kruthik.scm.entities.User;
import com.kruthik.scm.services.ContactService;
import com.kruthik.scm.services.SuggestionService;
import com.kruthik.scm.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProcessingController {

	private final UserService userService;
	private final ContactService contactService;
	private final SuggestionService suggestionService;

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

	@PostMapping("/user/add-contact")
	public String addContact(@Valid @ModelAttribute ContactDTO contactDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Authentication authentication) {

		/* To check if the form has validation errors */
		if (bindingResult.hasErrors()) {
			return "user/addContact";
		}

		/* To fetch the current logged in user */
		String email = authentication.getName();
		User userByEmail = userService.findUserByEmail(email);

		/* To Set the current logged in user to contact */
		contactDTO.setUser(userByEmail);

		/* To Save the contact in the DB */
		Contact contact = contactService.addContact(contactDTO, email);

		if (contact != null) {
			redirectAttributes.addFlashAttribute("toastMessage", "Contact Saved Successfully!");
			redirectAttributes.addFlashAttribute("toastType", "success");
		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Failed to Save Contact!");
			redirectAttributes.addFlashAttribute("toastType", "error");
		}

		return "redirect:/user/add-contact";
	}

	@PostMapping("/user/update-contact")
	public String updateContact(@Valid @ModelAttribute ContactDTO contactDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Authentication authentication) {

		/* To check if the form has validation errors */
		if (bindingResult.hasErrors()) {
			return "user/updateContact";
		}

		/* To fetch the current logged in user */
		String email = authentication.getName();
		User userByEmail = userService.findUserByEmail(email);

		/* To Set the current logged in user to contact */
		contactDTO.setUser(userByEmail);

		/* To Save the contact in the DB */
		int updatedRows = contactService.updateContact(contactDTO, email);

		if (updatedRows > 0) {
			redirectAttributes.addFlashAttribute("toastMessage", "Contact Updated Successfully!");
			redirectAttributes.addFlashAttribute("toastType", "success");
		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Failed to Update Contact!");
			redirectAttributes.addFlashAttribute("toastType", "error");
		}

		return "redirect:/user/view-contacts";
	}

	@PostMapping("/user/suggestion")
	public String suggestion(@Valid @ModelAttribute SuggestionDTO suggestionDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "user/suggestion";
		}

		Suggestion saveSuggestion = suggestionService.saveSuggestion(suggestionDTO);

		if (saveSuggestion != null) {
			redirectAttributes.addFlashAttribute("toastMessage", "Suggestion Sent Successfully!");
			redirectAttributes.addFlashAttribute("toastType", "success");
			return "redirect:/user/profile";
		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Failed to send Suggestion!");
			redirectAttributes.addFlashAttribute("toastType", "warning");
			return "redirect:/user/suggestion";
		}
	}

	@PostMapping("/update-account")
	public String updateAccount(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "user/updateUser";
		}

		int updateUser = userService.updateUser(userDTO);

		if (updateUser > 0) {
			redirectAttributes.addFlashAttribute("toastMessage", "Details Updated Successfully!");
			redirectAttributes.addFlashAttribute("toastType", "success");
		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Failed to Update Details!");
			redirectAttributes.addFlashAttribute("toastType", "error");
		}

		return "redirect:/user/profile";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@Valid @ModelAttribute ResetPasswordDTO resetPasswordDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "redirect:/resetPassword";
		}

		int updatedRows = userService.resetPassword(resetPasswordDTO);

		if (updatedRows > 0) {
			redirectAttributes.addFlashAttribute("toastMessage", "Password Reset Successfully!");
			redirectAttributes.addFlashAttribute("toastType", "success");
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("toastMessage", "Either email or phone number is wrong!");
			redirectAttributes.addFlashAttribute("toastType", "error");
			return "redirect:/resetPassword";
		}

	}

}
