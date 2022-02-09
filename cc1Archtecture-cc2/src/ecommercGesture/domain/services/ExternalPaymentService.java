package ecommercGesture.domain.services;

import ecommercGesture.domain.objects.BillingInformation;


public class ExternalPaymentService {
	
	public ExternalPaymentService() {}

	public boolean proceedToPayment(BillingInformation billing, double transactionPrice) {
		// simulate payment from an external bank service
		return true;
	}

}
