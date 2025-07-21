package com.kruthik.scm.dtos;

import java.time.LocalDate;

import com.kruthik.scm.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {
	private Integer id;
	private String name;
	private String email;
	private String phoneNumber;
	private String profilePic;
	private boolean favourite = false;
	private LocalDate dob;

	private User user;
}
