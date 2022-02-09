package ecommercGesture.domain.objects;

public class MembershipDetails {

	private final double applictionPrice;
	private final int membershipDuration;// in day
	
	private MembershipDetails(double applictionPrice, int membershipDuration) {
		this.applictionPrice = applictionPrice;
		this.membershipDuration = membershipDuration;
	}
	
    public static MembershipDetails of(double applictionPrice, int membershipDuration) {
        return new MembershipDetails(applictionPrice, membershipDuration);
    }

	public double getApplictionPrice() {
		return applictionPrice;
	}

	public int getMembershipDuration() {
		return membershipDuration;
	}

	@Override
	public String toString() {
		return "MembershipDetails [applictionPrice=" + applictionPrice + ", membershipDuration=" + membershipDuration
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(applictionPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + membershipDuration;
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
		MembershipDetails other = (MembershipDetails) obj;
		if (Double.doubleToLongBits(applictionPrice) != Double.doubleToLongBits(other.applictionPrice))
			return false;
		if (membershipDuration != other.membershipDuration)
			return false;
		return true;
	}

}
