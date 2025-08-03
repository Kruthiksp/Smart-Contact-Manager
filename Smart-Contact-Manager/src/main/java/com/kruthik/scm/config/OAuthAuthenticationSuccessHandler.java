package com.kruthik.scm.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.enums.Providers;
import com.kruthik.scm.services.UserService;
import com.kruthik.scm.util.SocialLoginConstants;

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

		// Identifying the Provider

		OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

		String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

		if (authorizedClientRegistrationId.equals(SocialLoginConstants.GOOGLE)) {

			OAuth2User googleUser = oAuth2AuthenticationToken.getPrincipal();

			UserDTO userDTO = UserDTO.builder().name(googleUser.getAttribute("name"))
					.email(googleUser.getAttribute("email")).profilePic(googleUser.getAttribute("picture"))
					.emailVerified(googleUser.getAttribute("email_verified")).provider(Providers.GOOGLE).enabled(true)
					.build();

			userService.saveUser(userDTO);

		} else if (authorizedClientRegistrationId.equals(SocialLoginConstants.GITHUB)) {

			OAuth2User gitHubUser = oAuth2AuthenticationToken.getPrincipal();

			UserDTO userDTO = UserDTO.builder().name(gitHubUser.getAttribute("login"))
					.profilePic(gitHubUser.getAttribute("avatar_url")).emailVerified(true).provider(Providers.GITHUB)
					.enabled(true).build();

			if (gitHubUser.getAttribute("email") == null) {
				String email = gitHubUser.getAttribute("login") + "@github.com";
				userDTO.setEmail(email);
				userDTO.setEmailVerified(false);
			}
			userService.saveUser(userDTO);
		}

		response.sendRedirect("/home");
//		new DefaultRedirectStrategy().sendRedirect(request, response, "/home");
	}

}
