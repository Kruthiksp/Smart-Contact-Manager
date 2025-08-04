package com.kruthik.scm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kruthik.scm.entities.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

	Page<Suggestion> findAll(Pageable pageable);

	Page<Suggestion> findAllByUserEmailIdContainingIgnoreCase(String keyword, Pageable pageable);

}
