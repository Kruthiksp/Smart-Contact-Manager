package com.kruthik.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruthik.scm.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
