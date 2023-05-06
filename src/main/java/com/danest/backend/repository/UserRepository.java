package com.danest.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.danest.backend.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
