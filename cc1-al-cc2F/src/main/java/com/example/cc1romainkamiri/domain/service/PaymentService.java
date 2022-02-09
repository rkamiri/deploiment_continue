package com.example.cc1romainkamiri.domain.service;

import com.example.cc1romainkamiri.domain.entity.PaymentInfos;
import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Payment;
import com.example.cc1romainkamiri.domain.repository.PaymentRepository;


public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final ExternalPaymentService externalPaymentService;

	public PaymentService(PaymentRepository paymentRepository, ExternalPaymentService externalPaymentService) {
		this.paymentRepository = paymentRepository;
		this.externalPaymentService = externalPaymentService;
	}

    public Id getNextId() {
		return paymentRepository.getNextId();
	}
	
	public Payment getPaymentById(Id id) {
		return paymentRepository.getPaymentById(id);
	}

	public Payment addPayment(Payment payment) {
		return paymentRepository.savePayment(payment);
	}

	public boolean pay(PaymentInfos paymentInfos, double amount) {
		return externalPaymentService.transaction(paymentInfos,amount);
	}

}
