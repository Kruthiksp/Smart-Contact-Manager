package com.kruthik.scm.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kruthik.scm.entities.Suggestion;
import com.kruthik.scm.entities.User;
import com.kruthik.scm.services.SuggestionService;
import com.kruthik.scm.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final UserService userService;
	private final SuggestionService suggestionService;

	@GetMapping("/view-users")
	public String viewUsers(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(required = false) String keyword, Model model, Authentication authentication) {

		Page<User> allUsers;

		if (keyword != null && !keyword.isEmpty()) {
			allUsers = userService.findAllUsers(keyword, pageNumber, pageSize);
		} else {
			allUsers = userService.findAllUsers(pageNumber, pageSize);
		}

		/* Setting Contacts to model */
		model.addAttribute("users", allUsers.getContent()); // actual list of contacts
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", allUsers.getTotalPages());

		return "admin/viewUsers";
	}

	@GetMapping("/view-suggestions")
	public String viewSuggestions(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(required = false) String keyword, Model model, Authentication authentication) {

		Page<Suggestion> allSuggestions;

		if (keyword != null && !keyword.isEmpty()) {
			allSuggestions = suggestionService.findAllSuggestions(keyword, pageNumber, pageSize);
		} else {
			allSuggestions = suggestionService.findAllSuggestions(pageNumber, pageSize);
		}

		/* Setting Contacts to model */
		model.addAttribute("suggestions", allSuggestions.getContent()); // actual list of contacts
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", allSuggestions.getTotalPages());

		return "admin/viewSuggestions";
	}

}
