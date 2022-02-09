package ecommercGesture.domain.services;

import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Payment;


public class GlobalPaymentService {
	
	private final PaymentService paymentService;
	private final ExternalPaymentService externalPaymentService;
	
	public GlobalPaymentService(PaymentService paymentService, ExternalPaymentService externalPaymentService) {
		this.paymentService = paymentService;
		this.externalPaymentService = externalPaymentService;
	}
	
	public Id getNextId() {
		return paymentService.getNextId();
	}

	public Payment getPayment(Id id) {
		return paymentService.getPaymentById(id);
	}
	
	public Payment addPayment(Payment payment) {
		return paymentService.addPayment(payment);
	}
	
	public boolean pay(BillingInformation billing, double transactionPrice) {
		return externalPaymentService.proceedToPayment(billing,transactionPrice);
	}
}
