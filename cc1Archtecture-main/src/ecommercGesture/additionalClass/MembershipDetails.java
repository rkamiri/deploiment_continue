package ecommercGesture.additionalClass;

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
	
}
