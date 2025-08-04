package com.kruthik.scm.services.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kruthik.scm.dtos.SuggestionDTO;
import com.kruthik.scm.entities.Suggestion;
import com.kruthik.scm.repositories.SuggestionRepository;
import com.kruthik.scm.services.SuggestionService;
import com.kruthik.scm.util.SuggestionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

	private final SuggestionMapper suggestionMapper;
	private final SuggestionRepository suggestionRepository;

	@Override
	public Suggestion saveSuggestion(SuggestionDTO suggestionDTO) {
		Suggestion dtoToEntity = suggestionMapper.dtoToEntity(suggestionDTO);
		dtoToEntity.setSentDate(LocalDate.now());
		return suggestionRepository.save(dtoToEntity);
	}

	@Override
	public Page<Suggestion> findAllSuggestions(int pageNumber, int pageSize) {
		Pageable pagable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "sentDate"));
		return suggestionRepository.findAll(pagable);
	}

	@Override
	public Page<Suggestion> findAllSuggestions(String keyword, int pageNumber, int pageSize) {
		Pageable pagable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "sentDate"));
		return suggestionRepository.findAllByUserEmailIdContainingIgnoreCase(keyword, pagable);
	}

}
