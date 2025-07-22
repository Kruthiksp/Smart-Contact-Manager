package com.kruthik.scm.services.impl;

import org.springframework.stereotype.Service;

import com.kruthik.scm.entities.User;
import com.kruthik.scm.repositories.UserRepository;
import com.kruthik.scm.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
//	private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
