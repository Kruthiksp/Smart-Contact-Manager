package com.kruthik.scm.services;

import org.springframework.data.domain.Page;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;

public interface UserService {

	User saveUser(UserDTO userDTO);

	User findUserByEmail(String email);

	User findUserByVerificationToken(String token);

	int updateEnable(boolean enabled, boolean emailVerified, boolean phoneNumberVerified, String emailToken);

	Page<User> findAllUsers(int pageNumber, int pageSize);

	Page<User> findAllUsers(String keyword, int pageNumber, int pageSize);

}
