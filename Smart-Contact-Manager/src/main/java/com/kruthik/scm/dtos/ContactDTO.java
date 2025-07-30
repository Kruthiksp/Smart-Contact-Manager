package com.kruthik.scm.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.kruthik.scm.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "Name is required.")
	@Size(min = 3, message = "Mininum 3 characters required.")
	private String name;
	@NotBlank(message = "Email is required.")
	@Pattern(regexp = "^[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}", message = "Enter a Valid Email ID")
	private String email;
	@NotBlank(message = "Phone number is required.")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
	private String phoneNumber;
	private MultipartFile profilePic;
	private boolean favourite;
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Past(message = "Date of birth must be in the past.")
	private LocalDate dob;

	private User user;
}
