package com.kruthik.scm.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO entityToDto(User user);

	@Mapping(target = "id", ignore = true)
	User dtoToEntity(UserDTO userDTO);

}
