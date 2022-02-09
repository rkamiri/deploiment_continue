package com.example.cc1romainkamiri.infrastructure.exceptions;


import com.example.cc1romainkamiri.domain.entity.Id;

public class NotFoundPaymentInfo extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private NotFoundPaymentInfo(String message) {
        super(message);
    }

    public static NotFoundPaymentInfo withId(Id id) {
        return new NotFoundPaymentInfo("Unable to find payment info : " + id.getId());
    }
}
