package ecommercGesture.repositories;

import java.util.Optional;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.Payment;

public interface PaymentRepository {
	
	Id getNextId();
	Optional<Payment> getPaymentById(Id id);
	Id savePayment(Payment payment);

}
