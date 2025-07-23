package com.kruthik.scm.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;
import com.kruthik.scm.repositories.UserRepository;
import com.kruthik.scm.services.UserService;
import com.kruthik.scm.util.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	@Override
	public User saveUser(UserDTO userDTO) {
		
		User user = userMapper.dtoToEntity(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
}
