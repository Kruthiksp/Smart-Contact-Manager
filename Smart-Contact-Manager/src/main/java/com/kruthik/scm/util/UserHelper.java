package com.kruthik.scm.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

	@Value("${mailtrap.email.verification.link}")
	private String verificationUrl;

	/**
	 * @param principal
	 * @return String email
	 */
	public static String getEmailOfLoggedInUser(Authentication authentication) {

		String email = null;

		/*
		 * if this condition is true then java will automatically type cast it to a new
		 * variable ie, oAuth2AuthenticationToken
		 */
		if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {

//			OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
			OAuth2User oauthUser = oAuth2AuthenticationToken.getPrincipal();

			if (authorizedClientRegistrationId.equals(SocialLoginConstants.GOOGLE)) {
				email = oauthUser.getAttribute("email");
			} else if (authorizedClientRegistrationId.equals(SocialLoginConstants.GITHUB)) {

				email = (oauthUser.getAttribute("email") == null) ? oauthUser.getAttribute("login") + "@github.com"
						: oauthUser.getAttribute("email");

			}

		} else {
			email = authentication.getName();
		}

		return email;
	}

	public String generateLinkForEmailVerification(String token) {
		return verificationUrl + token;
	}
}
