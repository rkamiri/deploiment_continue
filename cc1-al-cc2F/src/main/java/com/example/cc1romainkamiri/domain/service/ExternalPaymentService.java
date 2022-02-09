package com.example.cc1romainkamiri.domain.service;

import com.example.cc1romainkamiri.domain.entity.PaymentInfos;


public class ExternalPaymentService {
	
	public ExternalPaymentService() {}

	public boolean transaction(PaymentInfos paymentInfos, double amountToPay) {
		//call external API to pay
		return true;
	}

}
