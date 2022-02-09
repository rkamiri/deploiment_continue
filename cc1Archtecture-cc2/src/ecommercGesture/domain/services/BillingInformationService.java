package ecommercGesture.domain.services;

import java.util.List;

import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.repositories.BillingInformationRepository;

public class BillingInformationService {

	private final BillingInformationRepository billingInformationRepository;
	
	public BillingInformationService(BillingInformationRepository billingInformationRepository) {
		this.billingInformationRepository = billingInformationRepository;
	}
	
	public Id getNextId() {
		return billingInformationRepository.getNextId();
	}
	
	public BillingInformation getbillingInformationById(Id id) {
		return billingInformationRepository.getBillingInformationById(id);
	}
	
	public BillingInformation getbillingInformationByUserId(Id userId) {
		return billingInformationRepository.getBillingInformationByUserId(userId);
	}
	
	public List<BillingInformation> getAll() {
		return billingInformationRepository.getAll();
	}
	
	public BillingInformation addBillingInformation(BillingInformation billingInformation) {
		return billingInformationRepository.saveBillingInformation(billingInformation);
	}
	
	public boolean billingInformationExist(Id id) {
		try {
			getbillingInformationById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
}
