package com.kruthik.scm.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kruthik.scm.services.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;

	@Value("${mailtrap.sender.mail}")
	private String senderMail;
	@Value("${mailtrap.verification.mail.subject}")
	private String mailSubject;

	@Override
	public void sendMail(String to, String body) {

		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setTo(to);
			mailMessage.setSubject(mailSubject);
			mailMessage.setText(body);
			mailMessage.setFrom(senderMail);

			javaMailSender.send(mailMessage);

		} catch (Exception e) {
			System.out.println("Exception occurred while sending email: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
