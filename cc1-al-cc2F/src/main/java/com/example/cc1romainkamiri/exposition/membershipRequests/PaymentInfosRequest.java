package com.example.cc1romainkamiri.exposition.membershipRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentInfosRequest {
	
    @NotNull
    @NotBlank
	public String cardNumber;
    
    @NotNull
    @NotBlank
	public String expirationDate;
    
    @NotNull
    @NotBlank
	public String secretPictogram;
}
