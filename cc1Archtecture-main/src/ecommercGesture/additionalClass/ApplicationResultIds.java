package ecommercGesture.additionalClass;

import ecommercGesture.objects.Id;

public class ApplicationResultIds {
	
	private final Id memberId;
	private final Id paymentId;
	
	private ApplicationResultIds(Id memberId, Id paymentId) {
		this.memberId = memberId;
		this.paymentId = paymentId;
	}
	
    public static ApplicationResultIds of(Id memberId, Id paymentId) {
        return new ApplicationResultIds( memberId, paymentId);
    }
	
	public Id getMemberId() {
		return memberId;
	}
	
	public Id getPaymentId() {
		return paymentId;
	}

}
