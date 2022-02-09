package ecommercGesture.defaultRepositoryImplementation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ecommercGesture.objects.Payment;
import ecommercGesture.objects.Id;
import ecommercGesture.repositories.PaymentRepository;

public class InMemoryPaymentRepository implements PaymentRepository {
	
	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, Payment> data = new ConcurrentHashMap<>();
	
	@Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }
	
	@Override
	public Optional<Payment> getPaymentById(Id id){
		return Optional.ofNullable(data.get(id));
	}
	
	@Override
	public Id savePayment(Payment payment) {
		data.put(payment.getId(), payment);
		return payment.getId();
	}

}