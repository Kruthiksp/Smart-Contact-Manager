package com.kruthik.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kruthik.scm.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailToken(String token);

	boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query("update User u set u.enabled=:enabled,u.emailVerified=:emailVerified,u.phoneNumberVerified=:phoneNumberVerified where emailToken=:emailToken")
	int updateEnable(boolean enabled, boolean emailVerified, boolean phoneNumberVerified, String emailToken);

}
