package com.example.cc1romainkamiri.exposition.membershipRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class ApplyMembershipRequest {

    @NotNull
	public int userId;
	
    @NotNull
	public InfosMembershipRequest infosMembershipRequest;
	
    @NotNull
	public PaymentInfosRequest paymentInfosRequest;
	
    @NotNull
	public LocalDate creationDate;
	
}
