package ecommercGesture.domain.services;

import java.util.List;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Payment;
import ecommercGesture.domain.repositories.PaymentRepository;


public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public Id getNextId() {
		return paymentRepository.getNextId();
	}
	
	public Payment getPaymentById(Id id) {
		return paymentRepository.getPaymentById(id);
	}
	
	public List<Payment> getAll() {
		return paymentRepository.getAll();
	}
	
	public Payment addPayment(Payment payment) {
		return paymentRepository.savePayment(payment);
	}
	
	public boolean paymentExist(Id id) {
		try {
			getPaymentById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}

}
