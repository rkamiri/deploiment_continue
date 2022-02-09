package ecommercGesture.domain.objects;

public class BillingInformation {

	private final Id id;
	private final Id userId;
	private final String cardNumber;
	private final String expirationDate;
	private final String secretPictogram;
	private final String ownerName;
	
	private BillingInformation(Id id, Id userId, String cardNumber, String expirationDate, String secretPictogram, String ownerName) {
		this.id = id;
		this.userId = userId;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.secretPictogram = secretPictogram;
		this.ownerName = ownerName;
	}
	
    public static BillingInformation of(Id id, Id userId, String cardNumber, String expirationDate, String secretPictogramme, String ownerName) {
        return new BillingInformation(id, userId, cardNumber, expirationDate, secretPictogramme, ownerName);
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

	public String getOwnerName() {
		return ownerName;
	}

	@Override
	public String toString() {
		return "BillingInformation [id=" + id + ", userId=" + userId + ", cardNumber=" + cardNumber
				+ ", expirationDate=" + expirationDate + ", secretPictogram=" + secretPictogram + ", ownerName="
				+ ownerName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((secretPictogram == null) ? 0 : secretPictogram.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingInformation other = (BillingInformation) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (secretPictogram == null) {
			if (other.secretPictogram != null)
				return false;
		} else if (!secretPictogram.equals(other.secretPictogram))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
