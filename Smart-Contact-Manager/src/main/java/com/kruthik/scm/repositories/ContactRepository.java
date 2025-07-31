package com.kruthik.scm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	Page<Contact> findByUser(User user, Pageable pageable);

	Page<Contact> findByUserAndNameContainingIgnoreCase(User user, String keyword, Pageable pageable);

}
