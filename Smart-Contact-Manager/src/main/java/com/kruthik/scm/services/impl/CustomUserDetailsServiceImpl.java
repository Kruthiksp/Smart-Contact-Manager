package com.kruthik.scm.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kruthik.scm.entities.User;
import com.kruthik.scm.repositories.UserRepository;
import com.kruthik.scm.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("CustomUserDetailsServiceImpl: " + username);
		
		User byEmail = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

		System.out.println("CustomUserDetailsServiceImpl: " + byEmail.getEmail());
		
		return new CustomUserDetails(byEmail);
	}

}
