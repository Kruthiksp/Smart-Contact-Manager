package com.kruthik.scm.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kruthik.scm.entities.User;
import com.kruthik.scm.enums.Role;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailToken(String token);

	boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query("update User u set u.enabled=:enabled,u.emailVerified=:emailVerified,u.phoneNumberVerified=:phoneNumberVerified where emailToken=:emailToken")
	int updateEnable(boolean enabled, boolean emailVerified, boolean phoneNumberVerified, String emailToken);

	@Transactional
	@Modifying
	@Query("update User u set u.role=:role where id=:userId")
	int assignAdminRole(int userId, Role role);

	@Transactional
	@Modifying
	@Query("update User u set u.name=:name, u.phoneNumber=:phoneNumber,u.password=:password where u.id=:userId")
	int updateUser(int userId, String name, String phoneNumber, String password);

	Page<User> findAll(Pageable pageable);

	Page<User> findAllByNameContainingIgnoreCase(String keyword, Pageable pageable);

	@Transactional
	@Modifying
	@Query("update User u set u.password=:newPassword where u.email=:email and u.phoneNumber=:phoneNumber")
	int resetPassword(String email, String phoneNumber, String newPassword);
}
