package com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Payment;
import com.example.cc1romainkamiri.domain.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {
	
	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, Payment> paymentDbMimic = new HashMap<>();
	
	@Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }
	
	@Override
	public Payment getPaymentById(Id id){
		return paymentDbMimic.get(id);
	}

	
	@Override
	public Payment savePayment(Payment payment) {
		paymentDbMimic.put(payment.getId(), payment);
		return payment;
	}

}
