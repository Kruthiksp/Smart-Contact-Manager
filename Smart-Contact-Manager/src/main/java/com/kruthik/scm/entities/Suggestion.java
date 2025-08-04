package com.kruthik.scm.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int suggestionId;
	private String userEmailId;
	@Column(length = 2000, nullable = false)
	private String suggestionMessage;
	private LocalDate sentDate;
}
