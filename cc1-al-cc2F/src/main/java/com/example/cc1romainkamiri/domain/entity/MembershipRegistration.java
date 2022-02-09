package com.example.cc1romainkamiri.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

public class MembershipRegistration {
	
	private final int userId;
	private final MembershipInfos membershipInfos;
	private final PaymentInfos paymentInfos;
	private final LocalDate creationDate;
	
	public MembershipRegistration(int id, MembershipInfos membershipInfos, PaymentInfos paymentInfos, LocalDate creationDate) {
		this.userId = Objects.requireNonNull(id);
		this.membershipInfos = Objects.requireNonNull(membershipInfos);
		this.paymentInfos = Objects.requireNonNull(paymentInfos);
		this.creationDate = Objects.requireNonNull(creationDate);
	}

	public int getUserId() {
		return userId;
	}

	public MembershipInfos getMembershipInfos() {
		return membershipInfos;
	}

	public PaymentInfos getPaymentInfos() {
		return paymentInfos;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}


}
