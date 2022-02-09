package com.example.cc1romainkamiri.domain.entity;

public class MembershipInfos {

	private final double amount;
	private final int durationTime;
	
	private MembershipInfos(double amount, int membershipDuration) {
		this.amount = amount;
		this.durationTime = membershipDuration;
	}
	
    public static MembershipInfos of(double amount, int durationTime) {
        return new MembershipInfos(amount, durationTime);
    }

	public double getAmount() {
		return amount;
	}

	public int getDurationTime() {
		return durationTime;
	}

}
