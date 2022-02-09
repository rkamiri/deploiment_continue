package com.example.cc1romainkamiri.domain.entity;

public class PaymentInfos {

	private final Id id;
	private final Id userId;
	private final String cardNumber;
	private final String expirationDate;
	private final String secretPictogram;
	
	private PaymentInfos(Id id, Id userId, String cardNumber, String expirationDate, String secretPictogram) {
		this.id = id;
		this.userId = userId;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.secretPictogram = secretPictogram;
	}
	
    public static PaymentInfos of(Id id, Id userId, String cardNumber, String expirationDate, String secretPictogram) {
        return new PaymentInfos(id, userId, cardNumber, expirationDate, secretPictogram);
    }
    
	public Id getId() {
		return id;
	}

	public Id getUserId() {
		return userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public String getSecretPictogram() {
		return secretPictogram;
	}
	
}
