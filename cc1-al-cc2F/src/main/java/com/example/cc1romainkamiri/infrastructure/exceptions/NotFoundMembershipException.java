package com.example.cc1romainkamiri.infrastructure.exceptions;

import com.example.cc1romainkamiri.domain.entity.Id;

public class NotFoundMembershipException extends Exception{
    public NotFoundMembershipException(Id userId){
        super("Membership with id : " + userId.getId() + " not found.");
    }
}
