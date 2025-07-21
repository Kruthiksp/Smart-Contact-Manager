package com.kruthik.scm.dtos;

import java.util.List;

import com.kruthik.scm.entities.Contact;
import com.kruthik.scm.enums.Providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	
	private Integer Id;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private String profilePic;
	private boolean enabled = false;
	private boolean emailVerified = false;
	private boolean phoneNumberVerified = false;
	private Providers provider = Providers.SELF;
	private String providerId;
	private List<Contact> contacts;
}
