package com.example.cc1romainkamiri.infrastructure.exceptions;

public class IncorrectMembershipInfos extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private IncorrectMembershipInfos(String message) {
        super(message);
    }

    public static IncorrectMembershipInfos withInfo(String info) {
        return new IncorrectMembershipInfos("membership has bad " + info + " info");
    }
}
