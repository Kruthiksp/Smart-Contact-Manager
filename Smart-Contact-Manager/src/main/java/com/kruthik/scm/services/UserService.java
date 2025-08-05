package com.kruthik.scm.services;

import org.springframework.data.domain.Page;

import com.kruthik.scm.dtos.ResetPasswordDTO;
import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;

public interface UserService {

	User saveUser(UserDTO userDTO);

	UserDTO findById(int userId);

	User findUserByEmail(String email);

	User findUserByVerificationToken(String token);

	int updateEnable(boolean enabled, boolean emailVerified, boolean phoneNumberVerified, String emailToken);

	Page<User> findAllUsers(int pageNumber, int pageSize);

	Page<User> findAllUsers(String keyword, int pageNumber, int pageSize);

	void deleteById(int userId);

	int updateUser(UserDTO userDTO);

	int resetPassword(ResetPasswordDTO resetPasswordDTO);
}
