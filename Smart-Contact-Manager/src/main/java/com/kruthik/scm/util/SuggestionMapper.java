package com.kruthik.scm.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruthik.scm.dtos.SuggestionDTO;
import com.kruthik.scm.entities.Suggestion;

@Mapper(componentModel = "spring")
public interface SuggestionMapper {

	@Mapping(target = "suggestionId", ignore = true)
	Suggestion dtoToEntity(SuggestionDTO suggestionDTO);

	@Mapping(target = "suggestionId", ignore = true)
	SuggestionDTO entityToDto(Suggestion suggestion);
}
