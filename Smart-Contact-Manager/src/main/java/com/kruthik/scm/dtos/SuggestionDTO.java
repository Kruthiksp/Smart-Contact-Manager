package com.kruthik.scm.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuggestionDTO {

	private int suggestionId;
	private String userEmailId;
	@NotBlank(message = "Suggestion should not be empty.")
	private String suggestionMessage;
	private LocalDate sentDate;
}
