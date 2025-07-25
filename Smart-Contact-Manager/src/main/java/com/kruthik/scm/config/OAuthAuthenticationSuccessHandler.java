package com.kruthik.scm.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.enums.Providers;
import com.kruthik.scm.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("Entered OAuthAuthenticationSuccessHandler");

		DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();

		UserDTO userDTO = UserDTO.builder().name(principal.getAttribute("name")).email(principal.getAttribute("email"))
				.profilePic(principal.getAttribute("picture")).emailVerified(principal.getAttribute("email_verified"))
				.provider(Providers.GOOGLE).enabled(true).build();

		userService.saveUser(userDTO);

		System.out.println("Exiting OAuthAuthenticationSuccessHandler");

		response.sendRedirect("/home");
//		new DefaultRedirectStrategy().sendRedirect(request, response, "/home");
	}

}
