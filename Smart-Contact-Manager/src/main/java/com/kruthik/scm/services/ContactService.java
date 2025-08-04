package com.kruthik.scm.services;

import org.springframework.data.domain.Page;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.dtos.ContactDTO_ForRetrieving;
import com.kruthik.scm.entities.Contact;

public interface ContactService {

	Contact addContact(ContactDTO contactDTO, String userEmail);

	ContactDTO_ForRetrieving findContact(int contactId);

	Page<Contact> getAllContactsByUserId(String email, int pageNumber, int pageSize);

	Page<Contact> getAllContactsByUserId(String email, String keyword, int pageNumber, int pageSize);

	Page<Contact> getFavoriteContactsByUserId(String email, int pageNumber, int pageSize);

	Page<Contact> getFavoriteContactsByUserId(String email, String keyword, int pageNumber, int pageSize);
	
	int updateContact(ContactDTO contactDTO, String email);

	void deleteContact(int contactId);

}
