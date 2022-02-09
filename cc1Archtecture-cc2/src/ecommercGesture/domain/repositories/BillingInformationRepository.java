package ecommercGesture.domain.repositories;

import java.util.List;

import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;

public interface BillingInformationRepository {

	Id getNextId();
	BillingInformation getBillingInformationById(Id id);
	List<BillingInformation> getAll();
	BillingInformation saveBillingInformation(BillingInformation billingInformation);
	BillingInformation getBillingInformationByUserId(Id userId);
}
