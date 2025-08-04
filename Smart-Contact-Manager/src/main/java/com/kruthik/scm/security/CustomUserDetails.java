package com.kruthik.scm.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kruthik.scm.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(() -> "ROLE_" + user.getRole().name());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}
