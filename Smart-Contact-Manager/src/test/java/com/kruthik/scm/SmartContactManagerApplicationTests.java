package com.kruthik.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kruthik.scm.services.EmailService;

@SpringBootTest
class SmartContactManagerApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	void contextLoads() {
	}

	@Test
	void sendEmailTest() {
		emailService.sendMail("kruthiksp62@gmail.com", "This is a SCM verification link");
	}

}
