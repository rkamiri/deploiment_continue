package ecommercGesture.services;

import ecommercGesture.additionalClass.BillingInformation;
import ecommercGesture.objects.Payment;
import ecommercGesture.objects.Id;

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
	
	public Id addPayment(Payment payment) {
		return paymentService.addPayment(payment);
	}
	
	public boolean pay(BillingInformation billing, double transactionPrice) {
		return externalPaymentService.proceedToPayment(billing,transactionPrice);
	}
}
