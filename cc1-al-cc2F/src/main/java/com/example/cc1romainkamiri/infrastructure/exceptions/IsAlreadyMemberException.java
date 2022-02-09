package com.example.cc1romainkamiri.infrastructure.exceptions;

import com.example.cc1romainkamiri.domain.entity.Id;

public class IsAlreadyMemberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private IsAlreadyMemberException(String message) {
        super(message);
    }

    public static IsAlreadyMemberException withId(Id id) {
        return new IsAlreadyMemberException("User " + id.getId() + "is already a subscribed member");
    }
}
