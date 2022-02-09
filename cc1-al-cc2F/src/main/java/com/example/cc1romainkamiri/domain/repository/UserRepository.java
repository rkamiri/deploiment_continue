package com.example.cc1romainkamiri.domain.repository;

import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Id userId);
    Id addUser(User user);
    void deleteUser(User user);
    void saveUser(User user);
    Id getNextId();
}
