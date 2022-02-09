package com.example.cc1romainkamiri.domain.repository;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Payment;

import java.util.List;


public interface PaymentRepository {
	
	Id getNextId();
	Payment getPaymentById(Id id);
	Payment savePayment(Payment payment);

}
