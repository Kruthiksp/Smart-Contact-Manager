package com.kruthik.scm.services;

import org.springframework.data.domain.Page;

import com.kruthik.scm.dtos.SuggestionDTO;
import com.kruthik.scm.entities.Suggestion;

public interface SuggestionService {

	Suggestion saveSuggestion(SuggestionDTO suggestionDTO);

	Page<Suggestion> findAllSuggestions(int pageNumber, int pageSize);

	Page<Suggestion> findAllSuggestions(String keyword, int pageNumber, int pageSize);
}
