package com.example.cc1romainkamiri.domain.service;

import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.repository.UserRepository;
import com.example.cc1romainkamiri.domain.entity.User;
import com.example.cc1romainkamiri.infrastructure.exceptions.NotFoundUserException;

import java.util.Optional;

public class UserService {

    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Id addUser(User user) {
        return userRepository.addUser(user);
    }

    public Optional<User> getUser(Id id) {
        return this.userRepository.findById(id);
    }

    public void removeUser(User user) {
        userRepository.deleteUser(user);
    }

    public void editUser(User user){
        //todo get a user and replace previous user by new one (using it's id)
        userRepository.saveUser(user);
    }

    public Id getNextId(){
        return this.userRepository.getNextId();
    }

}
