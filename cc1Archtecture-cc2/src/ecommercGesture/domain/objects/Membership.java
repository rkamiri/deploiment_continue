package ecommercGesture.domain.objects;

import java.time.LocalDate;

public class Membership {
	
	private final Id id;
	private final Id userId;
	private final double price;
	private LocalDate startMembership;
	private LocalDate endMembership;
	private boolean automaticMembershipRenew;
	
	private Membership(Id id, Id userId, double price, LocalDate startMembership, LocalDate endMembership, boolean automaticMembershipRenew) {
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.startMembership = startMembership;
		this.endMembership = endMembership;
		this.automaticMembershipRenew = automaticMembershipRenew;
	}
	
    public static Membership of(Id id, Id userId, double price, LocalDate startMembership, LocalDate endMembership, boolean automaticMembershipRenew) {
        return new Membership(id, userId, price, startMembership, endMembership, automaticMembershipRenew);
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

	public LocalDate getStartMembership() {
		return startMembership;
	}

	public LocalDate getEndMembership() {
		return endMembership;
	}

	public boolean isAutomaticMembershipRenew() {
		return automaticMembershipRenew;
	}

	public void setAutomaticMembershipRenew(boolean automaticMembershipRenew) {
		this.automaticMembershipRenew = automaticMembershipRenew;
	}

	@Override
	public String toString() {
		return "Membership [id=" + id + ", userId=" + userId + ", price=" + price + ", startMembership="
				+ startMembership + ", endMembership=" + endMembership + ", automaticMembershipRenew="
				+ automaticMembershipRenew + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (automaticMembershipRenew ? 1231 : 1237);
		result = prime * result + ((endMembership == null) ? 0 : endMembership.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startMembership == null) ? 0 : startMembership.hashCode());
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
		Membership other = (Membership) obj;
		if (automaticMembershipRenew != other.automaticMembershipRenew)
			return false;
		if (endMembership == null) {
			if (other.endMembership != null)
				return false;
		} else if (!endMembership.equals(other.endMembership))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (startMembership == null) {
			if (other.startMembership != null)
				return false;
		} else if (!startMembership.equals(other.startMembership))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
