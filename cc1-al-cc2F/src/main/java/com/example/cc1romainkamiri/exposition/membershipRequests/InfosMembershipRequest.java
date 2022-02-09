package com.example.cc1romainkamiri.exposition.membershipRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class InfosMembershipRequest {
	
    @NotNull
    @PositiveOrZero
	public double amount;
    
    @NotNull
    @Positive
	public int durationTime;

}
