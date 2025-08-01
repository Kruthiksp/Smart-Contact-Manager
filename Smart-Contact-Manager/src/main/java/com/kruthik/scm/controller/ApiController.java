package com.kruthik.scm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruthik.scm.dtos.ContactDTO_ForRetrieving;
import com.kruthik.scm.services.ContactService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

	private final ContactService contactService;

	@GetMapping("/contact/{contactId}")
	public ContactDTO_ForRetrieving getContact(@PathVariable int contactId) {
		return contactService.findContact(contactId);
	}
}
