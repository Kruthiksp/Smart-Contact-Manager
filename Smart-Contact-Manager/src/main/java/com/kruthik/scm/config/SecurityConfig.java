package com.kruthik.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.kruthik.scm.exceptions.handler.AuthFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	private final AuthFailureHandler authFailureHandler;


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/js/**", "/css/**", "/images/**","/", "/login", "/signup", "/do-register", "/home", "/auth/**")
				.permitAll().anyRequest().authenticated())
			.userDetailsService(userDetailsService)
			.formLogin(form -> form.loginPage("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/home", true)
				.failureUrl("/login?error=true")
				.permitAll()
				.failureHandler(authFailureHandler))
			.oauth2Login(oauth -> oauth.loginPage("/login").successHandler(authenticationSuccessHandler))
		/* .oauth2Client(Customizer.withDefaults()) */;

		return http.build();
	}

	AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(daoAuthenticationProvider);
	}

}
