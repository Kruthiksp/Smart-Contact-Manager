package com.kruthik.scm.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.dtos.ContactDTO_ForRetrieving;
import com.kruthik.scm.dtos.SuggestionDTO;
import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.services.ContactService;
import com.kruthik.scm.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final ContactService contactService;
	private final UserService userService;

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
		return "user/addContact";
	}

	@GetMapping("/view-contact/{contactId}")
	public String viewContactModal(@PathVariable int contactId, Model model) {

		ContactDTO_ForRetrieving contact = contactService.findContact(contactId);

		if (contact != null) {
			model.addAttribute("contact", contact);
		}

		return "user/contactModel";
	}

	@GetMapping("/view-contacts")
	public String viewContacts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(required = false) String keyword, Model model, Authentication authentication) {

		/* Fetching the current logged in user's email id */
		String email = authentication.getName();

		/* Fetching All the Contacts */
		Page<Contact> allContactsByUserId = contactService.getAllContactsByUserId(email, pageNumber, pageSize);

		if (keyword != null && !keyword.isEmpty()) {
			allContactsByUserId = contactService.getAllContactsByUserId(email, keyword, pageNumber, pageSize);
			model.addAttribute("keyword", keyword);
		} else {
			allContactsByUserId = contactService.getAllContactsByUserId(email, pageNumber, pageSize);
		}

		/* Setting Contacts to model */
		model.addAttribute("contacts", allContactsByUserId.getContent()); // actual list of contacts
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", allContactsByUserId.getTotalPages());

		return "user/viewContacts";
	}

	@GetMapping("/favorite-contacts")
	public String favoriteContacts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(required = false) String keyword, Model model, Authentication authentication) {

		/* Fetching the current logged in user's email id */
		String email = authentication.getName();

		/* Fetching All the Contacts */
		Page<Contact> allContactsByUserId = contactService.getAllContactsByUserId(email, pageNumber, pageSize);

		if (keyword != null && !keyword.isEmpty()) {
			allContactsByUserId = contactService.getFavoriteContactsByUserId(email, keyword, pageNumber, pageSize);
			model.addAttribute("keyword", keyword);
		} else {
			allContactsByUserId = contactService.getFavoriteContactsByUserId(email, pageNumber, pageSize);
		}

		/* Setting Contacts to model */
		model.addAttribute("contacts", allContactsByUserId.getContent()); // actual list of contacts
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", allContactsByUserId.getTotalPages());

		return "user/viewContacts";
	}

	@GetMapping("/update-contact/{contactId}")
	public String updateContact(@PathVariable int contactId, Model model) {

		ContactDTO_ForRetrieving contact = contactService.findContact(contactId);

		model.addAttribute("contactDTO", contact);

		return "/user/updateContact";
	}

	@GetMapping("/delete-contact/{contactId}")
	public String deleteContact(@PathVariable int contactId, RedirectAttributes redirectAttributes) {

		contactService.deleteContact(contactId);
		redirectAttributes.addFlashAttribute("toastMessage", "Contact Deleted Successfully!");
		redirectAttributes.addFlashAttribute("toastType", "success");

		return "redirect:/user/view-contacts";
	}

	@GetMapping("/suggestion")
	public String suggestion(Model model, Principal principal) {

		String loggedInUserEmail = principal.getName();

		SuggestionDTO suggestionDTO = new SuggestionDTO();
		suggestionDTO.setUserEmailId(loggedInUserEmail);
		model.addAttribute("suggestion", suggestionDTO);
		return "/user/suggestion";
	}

}

