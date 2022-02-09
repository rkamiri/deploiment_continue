package com.example.cc1romainkamiri.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
	
	private final Id id;
	private final Id userId;
	private final double amount;
	private final LocalDate creationDate;
	
	private Payment(Id id, Id user, double transactionPrice, LocalDate date) {
		this.id = id;
		this.userId = Objects.requireNonNull(user);
		this.amount = transactionPrice;
		this.creationDate = Objects.requireNonNull(date);
	}
	
    public static Payment of(Id id, Id user, double amount,LocalDate date) {
        return new Payment(id, user, amount, date);
    }

	public Id getId() {
		return id;
	}
	
	public Id getMember() {
		return userId;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
}
