package com.kruthik.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruthik.scm.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
