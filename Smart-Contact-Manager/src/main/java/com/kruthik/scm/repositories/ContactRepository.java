package com.kruthik.scm.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.entities.User;

import jakarta.transaction.Transactional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	Page<Contact> findByUser(User user, Pageable pageable);

	Page<Contact> findByUserAndNameContainingIgnoreCase(User user, String keyword, Pageable pageable);

	@Transactional
	@Modifying
	@Query("UPDATE Contact c SET " +
	       "c.name = :name, " +
	       "c.email = :email, " +
	       "c.phoneNumber = :phoneNumber, " +
	       "c.profilePic = :profilePic, " +
	       "c.dob = :dob, " +
	       "c.favourite = :favourite, " +
	       "c.user = :user " +
	       "WHERE c.id = :id")
	int updateContactById(@Param("id") int id,
	                       @Param("name") String name,
	                       @Param("email") String email,
	                       @Param("phoneNumber") String phoneNumber,
	                       @Param("profilePic") String profilePic,
	                       @Param("dob") LocalDate dob,
	                       @Param("favourite") boolean favourite,
	                       @Param("user") User user);

}
