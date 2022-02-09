package com.example.cc1romainkamiri.domain.entity;

import java.time.LocalDate;

public class Membership {
	
	private final Id id;
	private final Id userId;
	private final double price;
	private LocalDate startDateMembership;
	private LocalDate endDateMembership;
	private boolean autoRenewal;
	
	private Membership(Id id, Id userId, double price, LocalDate startDateMembership, LocalDate endDateMembership, boolean autoRenewal) {
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.startDateMembership = startDateMembership;
		this.endDateMembership = endDateMembership;
		this.autoRenewal = autoRenewal;
	}
	
    public static Membership of(Id id, Id userId, double price, LocalDate startDateMembership, LocalDate endDateMembership, boolean autoRenewal) {
        return new Membership(id, userId, price, startDateMembership, endDateMembership, autoRenewal);
    }
	
	public Id getId() {
		return id;
	}
	
	public Id getUserId() {
		return userId;
	}

	public double getPrice() {
		return price;
	}

	public LocalDate getStartDateMembership() {
		return startDateMembership;
	}

	public LocalDate getEndDateMembership() {
		return endDateMembership;
	}

	public boolean isAutoRenewal() {
		return autoRenewal;
	}

	public void setAutoRenewal(boolean autoRenewal) {
		this.autoRenewal = autoRenewal;
	}
}
