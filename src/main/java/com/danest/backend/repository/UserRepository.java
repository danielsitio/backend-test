package com.danest.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.danest.backend.domain.Profile;
import com.danest.backend.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u.profile FROM User u WHERE u.id=1")
    Profile findOnlyUserProfile();

    @Query("SELECT u FROM User u WHERE u.id=1")
    User findOnlyUser();
}
