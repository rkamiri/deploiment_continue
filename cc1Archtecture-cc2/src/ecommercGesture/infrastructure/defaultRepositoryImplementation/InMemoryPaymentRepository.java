package ecommercGesture.infrastructure.defaultRepositoryImplementation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Payment;
import ecommercGesture.domain.repositories.PaymentRepository;
import kernel.NoSuchEntityException;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {
	
	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, Payment> data = new ConcurrentHashMap<>();
	
	@Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }
	
	@Override
	public Payment getPaymentById(Id id){
		Payment payment = data.get(id);
		if (payment == null) {
			throw NoSuchEntityException.withIdAndElem(id,"payment");
		}
		return payment;
	}
	
	@Override
	public List<Payment> getAll() {
		List<Payment> result = data.values().stream().collect(Collectors.toList());
		return  result;
	}
	
	@Override
	public Payment savePayment(Payment payment) {
		data.put(payment.getId(), payment);
		return payment;
	}

}