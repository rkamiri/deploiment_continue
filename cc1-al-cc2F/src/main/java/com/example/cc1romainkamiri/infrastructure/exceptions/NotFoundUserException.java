package com.example.cc1romainkamiri.infrastructure.exceptions;

import com.example.cc1romainkamiri.domain.entity.Id;

public class NotFoundUserException extends Exception{
    public NotFoundUserException(Id userId){
        super("User with id : " + userId.getId() + " not found.");
    }
}
