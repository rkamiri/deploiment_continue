package ecommercGesture.domain.objects;

import java.time.LocalDate;
import java.util.Objects;

public class MembershipApplication {
	
	private final int userId;
	private final MembershipDetails membershipDetails;
	private final BillingInformation billing;
	private final LocalDate applicationDate;
	
	public MembershipApplication(int id, MembershipDetails membershipDetails, BillingInformation billing, LocalDate applicationDate) {
		this.userId = Objects.requireNonNull(id,"Appliant user id must be not null");
		this.membershipDetails = Objects.requireNonNull(membershipDetails,"Membership details must be not null");
		this.billing = Objects.requireNonNull(billing,"Billing information must be not null");
		this.applicationDate = Objects.requireNonNull(applicationDate,"Application date must be not null");
	}

	public int getUserId() {
		return userId;
	}

	public MembershipDetails getMembershipDetails() {
		return membershipDetails;
	}

	public BillingInformation getBilling() {
		return billing;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	@Override
	public String toString() {
		return "MembershipApplication [userId=" + userId + ", membershipDetails=" + membershipDetails + ", billing="
				+ billing + ", applicationDate=" + applicationDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationDate == null) ? 0 : applicationDate.hashCode());
		result = prime * result + ((billing == null) ? 0 : billing.hashCode());
		result = prime * result + ((membershipDetails == null) ? 0 : membershipDetails.hashCode());
		result = prime * result + userId;
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
		MembershipApplication other = (MembershipApplication) obj;
		if (applicationDate == null) {
			if (other.applicationDate != null)
				return false;
		} else if (!applicationDate.equals(other.applicationDate))
			return false;
		if (billing == null) {
			if (other.billing != null)
				return false;
		} else if (!billing.equals(other.billing))
			return false;
		if (membershipDetails == null) {
			if (other.membershipDetails != null)
				return false;
		} else if (!membershipDetails.equals(other.membershipDetails))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
