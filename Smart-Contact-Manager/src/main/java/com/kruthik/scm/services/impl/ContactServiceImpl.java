package com.kruthik.scm.services.impl;

import org.springframework.stereotype.Service;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.repositories.ContactRepository;
import com.kruthik.scm.services.ContactService;
import com.kruthik.scm.services.ImageService;
import com.kruthik.scm.util.ContactMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

	private final ContactMapper contactMapper;
	private final ContactRepository contactRepository;
	private final ImageService imageService;

	@Override
	public Contact addContact(ContactDTO contactDTO, String userEmail) {

		String uploadImageUrl = imageService.uploadImage(userEmail, contactDTO.getName(), contactDTO.getProfilePic());

		Contact dtoToEntity = contactMapper.dtoToEntity(contactDTO);
		
		dtoToEntity.setProfilePic(uploadImageUrl);

		return contactRepository.save(dtoToEntity);
	}

}
