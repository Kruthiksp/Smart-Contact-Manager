package com.kruthik.scm.dtos;

import jakarta.validation.constraints.NotBlank;
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
public class ResetPasswordDTO {

	@NotBlank(message = "Email is required.")
	@Pattern(regexp = "^[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}", message = "Enter a Valid Email ID")
	private String email;

	@NotBlank(message = "Phone number is required.")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
	private String phoneNumber;

	@NotBlank(message = "Password is required.")
	@Size(min = 8, message = "Password must be atleast 8 characters long.")
	private String password;
}
