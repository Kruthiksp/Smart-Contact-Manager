package com.kruthik.scm.entities;

import java.util.List;

import com.kruthik.scm.enums.Providers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(name = "username", nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false, length = 10)
	private String phoneNumber;
	@Column(nullable = false)
	private String password;
	@Column(length = 2000)
	private String profilePic;
	private boolean enabled = false;
	private boolean emailVerified = false;
	private boolean phoneNumberVerified = false;
	@Enumerated(EnumType.STRING)
	private Providers provider = Providers.SELF;
	private String providerId;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contact> contacts;
}
