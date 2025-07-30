package com.kruthik.scm.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruthik.scm.dtos.ContactDTO;
import com.kruthik.scm.entities.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "profilePic", ignore = true)
	ContactDTO entityToDto(Contact contact);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "profilePic", ignore = true)
	Contact dtoToEntity(ContactDTO contactDTO);

}
