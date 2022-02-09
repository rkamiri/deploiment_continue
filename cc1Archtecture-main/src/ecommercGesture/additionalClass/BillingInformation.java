package ecommercGesture.additionalClass;

import java.util.Objects;

public class BillingInformation {

	private final String cardNumber;
	private final String expirationDate;
	private final String secretPictogram;
	private final String ownerName;
	
	private BillingInformation(String cardNumber, String expirationDate, String secretPictogram, String ownerName) {
		this.cardNumber = Objects.requireNonNull(cardNumber,"Card number must be not null");
		this.expirationDate = Objects.requireNonNull(expirationDate,"Expiration date must be not null");
		this.secretPictogram = Objects.requireNonNull(secretPictogram,"Secret pictogram must be not null");
		this.ownerName = Objects.requireNonNull(ownerName,"Owner name must be not null");
	}
	
    public static BillingInformation of(String cardNumber, String expirationDate, String secretPictogramme, String ownerName) {
        return new BillingInformation(cardNumber, expirationDate, secretPictogramme, ownerName);
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

	public String getOwnerName() {
		return ownerName;
	}
	
}
