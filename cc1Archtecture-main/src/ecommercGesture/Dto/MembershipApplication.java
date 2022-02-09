package ecommercGesture.Dto;

import java.time.LocalDate;
import java.util.Objects;

import ecommercGesture.additionalClass.BillingInformation;
import ecommercGesture.additionalClass.MembershipDetails;

public class MembershipApplication {
	
	private final int userId;
	private final MembershipDetails membershipDetails;
	private final BillingInformation billing;
	private final LocalDate applicationDate;
	
	public MembershipApplication(int id, MembershipDetails membershipDetails, BillingInformation billing, LocalDate applicationDate) {
		this.userId = Objects.requireNonNull(id,"Appliant user id must be not null");
		this.membershipDetails = Objects.requireNonNull(membershipDetails,"Membership details must be not null");
		this.billing = Objects.requireNonNull(billing,"Billing information must be not null");
		this.applicationDate = Objects.requireNonNull(applicationDate,"Application date must be not null");
	}

	public int getUserId() {
		return userId;
	}

	public MembershipDetails getMembershipDetails() {
		return membershipDetails;
	}

	public BillingInformation getBilling() {
		return billing;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

}
