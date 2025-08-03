package com.kruthik.scm.exceptions.handler;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		HttpSession session = request.getSession();

		if (exception instanceof DisabledException) {

			session.setAttribute("toastMessage", "Before logIn\nVerifiy your email by the link sent to your email Id");
			session.setAttribute("toastType", "warning");

			response.sendRedirect("/login");
		} else {

			session.setAttribute("toastMessage", "Invalid username or password");
			session.setAttribute("toastType", "error");

			response.sendRedirect("/login");
		}
	}

}
