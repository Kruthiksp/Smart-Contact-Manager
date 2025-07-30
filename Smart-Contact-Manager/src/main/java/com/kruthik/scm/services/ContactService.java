package com.kruthik.scm.services;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.entities.Contact;

public interface ContactService {

	Contact addContact(ContactDTO contactDTO, String userEmail);

}
