package com.kruthik.scm.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.dtos.ContactDTO_ForRetrieving;
import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.entities.User;
import com.kruthik.scm.exceptions.ContactNotFoundException;
import com.kruthik.scm.repositories.ContactRepository;
import com.kruthik.scm.repositories.UserRepository;
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
	private final UserRepository userRepository;

	@Override
	public Contact addContact(ContactDTO contactDTO, String userEmail) {

		String uploadImageUrl = null;

		if (contactDTO.getProfilePic() != null && !contactDTO.getProfilePic().isEmpty()) {
			uploadImageUrl = imageService.uploadImage(userEmail, contactDTO.getName(), contactDTO.getProfilePic());
		}

		Contact dtoToEntity = contactMapper.dtoToEntity(contactDTO);

		dtoToEntity.setProfilePic(uploadImageUrl);

		return contactRepository.save(dtoToEntity);
	}

	@Override
	public ContactDTO_ForRetrieving findContact(int contactId) {
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ContactNotFoundException("Contact Not Found"));

		return contactMapper.entityToDto(contact);

	}

	@Override
	public int updateContact(ContactDTO contactDTO, String userEmail) {

		Contact byId = contactRepository.findById(contactDTO.getId())
				.orElseThrow(() -> new ContactNotFoundException("Contact not found"));

		String profileUrl = byId.getProfilePic();

		if (contactDTO.getProfilePic() != null && !contactDTO.getProfilePic().isEmpty()) {
			profileUrl = imageService.uploadImage(userEmail, contactDTO.getName(), contactDTO.getProfilePic());
		}

		return contactRepository.updateContactById(contactDTO.getId(), contactDTO.getName(), contactDTO.getEmail(),
				contactDTO.getPhoneNumber(), profileUrl, contactDTO.getDob(), contactDTO.isFavourite(),
				contactDTO.getUser());
	}

	@Override
	public Page<Contact> getAllContactsByUserId(String email, int pageNumber, int pageSize) {

		User userByEmail = null;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));

		if (userRepository.existsByEmail(email)) {
			userByEmail = userRepository.findByEmail(email).orElse(null);
		}
		return contactRepository.findByUser(userByEmail, pageable);
	}

	@Override
	public void deleteContact(int contactId) {
		contactRepository.deleteById(contactId);
	}

	@Override
	public Page<Contact> getAllContactsByUserId(String email, String keyword, int pageNumber, int pageSize) {
		User userByEmail = null;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));

		if (userRepository.existsByEmail(email)) {
			userByEmail = userRepository.findByEmail(email).orElse(null);
		}
		return contactRepository.findByUserAndNameContainingIgnoreCase(userByEmail, keyword, pageable);
	}

	@Override
	public Page<Contact> getFavoriteContactsByUserId(String email, int pageNumber, int pageSize) {

		User userByEmail = null;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));

		if (userRepository.existsByEmail(email)) {
			userByEmail = userRepository.findByEmail(email).orElse(null);
		}
		return contactRepository.findByUserAndFavouriteTrue(userByEmail, pageable);

	}

	@Override
	public Page<Contact> getFavoriteContactsByUserId(String email, String keyword, int pageNumber, int pageSize) {
		User userByEmail = null;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));

		if (userRepository.existsByEmail(email)) {
			userByEmail = userRepository.findByEmail(email).orElse(null);
		}
		return contactRepository.findByUserAndFavouriteTrueAndNameContainingIgnoreCase(userByEmail, keyword, pageable);
	}

}
