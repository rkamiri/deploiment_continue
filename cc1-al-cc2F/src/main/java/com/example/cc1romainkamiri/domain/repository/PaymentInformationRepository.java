package com.example.cc1romainkamiri.domain.repository;


import com.example.cc1romainkamiri.domain.entity.PaymentInfos;
import com.example.cc1romainkamiri.domain.entity.Id;

public interface PaymentInformationRepository {

	Id getNextId();
	PaymentInfos getPaymentInformationById(Id id);
	PaymentInfos savePaymentInformation(PaymentInfos paymentInfos);
	PaymentInfos getPaymentInformationByUserId(Id userId);
}
