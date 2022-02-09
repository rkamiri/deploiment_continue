package com.example.cc1romainkamiri.domain.service;


import com.example.cc1romainkamiri.domain.entity.PaymentInfos;
import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.repository.PaymentInformationRepository;

public class PaymentInfosService {

	private final PaymentInformationRepository paymentInformationRepository;
	
	public PaymentInfosService(PaymentInformationRepository paymentInformationRepository) {
		this.paymentInformationRepository = paymentInformationRepository;
	}
	
	public Id getNextId() {
		return paymentInformationRepository.getNextId();
	}
	
	public PaymentInfos getPaymentInformationById(Id id) {
		return paymentInformationRepository.getPaymentInformationById(id);
	}
	
	public PaymentInfos getPaymentInformationByUserId(Id userId) {
		return paymentInformationRepository.getPaymentInformationByUserId(userId);
	}

	public PaymentInfos addPaymentInformation(PaymentInfos paymentInfos) {
		return paymentInformationRepository.savePaymentInformation(paymentInfos);
	}
}
