package ecommercGesture.exposition.memberDTO;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class MembershipApplyDTO {

    @NotNull
	public int userId;
	
    @NotNull
	public MembershipDetailsDTO membershipDetailsRequest;
	
    @NotNull
	public BillingInformationsDTO billingInformationsRequest;
	
    @NotNull
	public LocalDate applicationDate;
	
}
