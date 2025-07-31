package com.kruthik.scm.services;

import org.springframework.data.domain.Page;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.entities.Contact;

public interface ContactService {

	Contact addContact(ContactDTO contactDTO, String userEmail);

	Page<Contact> getAllContactsByUserId(String email, int pageNumber, int pageSize);

	Page<Contact> getAllContactsByUserId(String email, String keyword, int pageNumber, int pageSize);

	void deleteContact(int contactId);

}
