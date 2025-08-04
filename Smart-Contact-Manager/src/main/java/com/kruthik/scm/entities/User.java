package com.kruthik.scm.entities;

import java.util.List;

import com.kruthik.scm.enums.Providers;
import com.kruthik.scm.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
	private Integer id;
	@Column(name = "username", nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = true, length = 10)
	private String phoneNumber;
	@Column(nullable = true)
	private String password;
	@Column(length = 2000)
	private String profilePic;
	@Builder.Default
	private boolean enabled = false;
	@Builder.Default
	private boolean emailVerified = false;
	@Builder.Default
	private boolean phoneNumberVerified = false;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Providers provider = Providers.SELF;
	private String providerId;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contact> contacts;

	private String emailToken;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;
}
