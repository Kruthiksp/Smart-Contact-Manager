package com.kruthik.scm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

	@Value("${cloudinary.cloud.name}")
	private String cloudname;
	@Value("${cloudinary.api.key}")
	private String apiKey;
	@Value("${cloudinary.api.secret}")
	private String apiSecret;

	@Bean
	Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudname, "api_key", apiKey, "api_secret", apiSecret));
	}
}
