package ecommercGesture.services;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.Payment;
import ecommercGesture.repositories.PaymentRepository;

public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public Id getNextId() {
		return paymentRepository.getNextId();
	}
	
	public Payment getPaymentById(Id id) {
		return paymentRepository.getPaymentById(id).get();
	}
	
	public Id addPayment(Payment payment) {
		return paymentRepository.savePayment(payment);
	}
	
	public boolean paymentExist(Id id) {
		return paymentRepository.getPaymentById(id).isPresent();
	}

}
