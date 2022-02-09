package com.example.cc1romainkamiri.infrastructure.exceptions;

import com.example.cc1romainkamiri.domain.entity.Id;

public class UserNotMemberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private UserNotMemberException(String message) {
        super(message);
    }

    public static UserNotMemberException withId(Id id) {
        return new UserNotMemberException("User with id : " + id.getId() + "is not subscribed");
    }
}
