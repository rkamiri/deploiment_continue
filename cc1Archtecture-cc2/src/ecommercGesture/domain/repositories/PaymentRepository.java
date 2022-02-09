package ecommercGesture.domain.repositories;


import java.util.List;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Payment;

public interface PaymentRepository {
	
	Id getNextId();
	Payment getPaymentById(Id id);
	List<Payment> getAll();
	Payment savePayment(Payment payment);

}
