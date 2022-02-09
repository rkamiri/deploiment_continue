package com.example.cc1romainkamiri.infrastructure.exceptions;

public class stopRenewalMembershipFinished extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private stopRenewalMembershipFinished(String message) {
        super(message);
    }

    public static stopRenewalMembershipFinished error() {
        return new stopRenewalMembershipFinished(String.format("membership already finished"));
    }
}
