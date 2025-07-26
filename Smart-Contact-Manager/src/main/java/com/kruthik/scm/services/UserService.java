package com.kruthik.scm.services;

import com.kruthik.scm.dtos.UserDTO;
import com.kruthik.scm.entities.User;

public interface UserService {

	User saveUser(UserDTO userDTO);

	User findUserByEmail(String email);

}
