package ecommercGesture.objects;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
	
	private final Id id;
	private final User user;
	private final double transactionPrice;
	private final LocalDate paymentDate;
	
	private Payment(Id id, User user, double transactionPrice, LocalDate paymentDate) {
		this.id = id;
		this.user = Objects.requireNonNull(user,"User id must be not null");
		this.transactionPrice = Objects.requireNonNull(transactionPrice,"Transaction price must be not null");
		this.paymentDate = Objects.requireNonNull(paymentDate,"Payment date must be not null");
	}
	
    public static Payment of(Id id, User user, double transactionPrice,LocalDate paymentDate) {
        return new Payment(id, user, transactionPrice, paymentDate);
    }

	public Id getId() {
		return id;
	}
	
	public User getMember() {
		return user;
	}

	public double getTransactionPrice() {
		return transactionPrice;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user=" + user + ", transactionPrice=" + transactionPrice + ", paymentDate="
				+ paymentDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(transactionPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (Double.doubleToLongBits(transactionPrice) != Double.doubleToLongBits(other.transactionPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
