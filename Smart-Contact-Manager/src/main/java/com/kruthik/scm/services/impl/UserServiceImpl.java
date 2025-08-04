package com.kruthik.scm.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;
import com.kruthik.scm.enums.Role;
import com.kruthik.scm.exceptions.UserNotFoundException;
import com.kruthik.scm.repositories.UserRepository;
import com.kruthik.scm.services.UserService;
import com.kruthik.scm.util.UserHelper;
import com.kruthik.scm.util.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final EmailServiceImpl emailServiceImpl;
	private final UserHelper userHelper;

	@Override
	public User saveUser(UserDTO userDTO) {

		if (!userRepository.existsByEmail(userDTO.getEmail())) {
			User user = userMapper.dtoToEntity(userDTO);

			if (userDTO.getPassword() != null) {

				user.setPassword(passwordEncoder.encode(user.getPassword()));

				String verificationToken = UUID.randomUUID().toString();

				String linkForEmailVerification = userHelper.generateLinkForEmailVerification(verificationToken);

				user.setEmailToken(verificationToken);

				emailServiceImpl.sendMail(user.getEmail(), linkForEmailVerification);

				User savedUser = userRepository.save(user);

				if (savedUser.getId() == 1) {
					userRepository.assignAdminRole(savedUser.getId(), Role.ADMIN);
				}

				return savedUser;
			}
		}
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public User findUserByVerificationToken(String token) {

		return userRepository.findByEmailToken(token)
				.orElseThrow(() -> new UserNotFoundException("User Not found with this verification link"));

	}

	@Override
	public int updateEnable(boolean enabled, boolean emailVerified, boolean phoneNumberVerified, String emailToken) {
		return userRepository.updateEnable(enabled, emailVerified, phoneNumberVerified, emailToken);
	}

	@Override
	public Page<User> findAllUsers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<User> findAllUsers(String keyword, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
		return userRepository.findAllByNameContainingIgnoreCase(keyword, pageable);
	}

}
