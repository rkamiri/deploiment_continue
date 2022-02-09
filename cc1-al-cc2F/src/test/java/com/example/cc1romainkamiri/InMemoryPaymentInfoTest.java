package com.example.cc1romainkamiri;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.PaymentInfos;
import com.example.cc1romainkamiri.domain.service.PaymentInfosService;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryPaymentInformationRepository;
import org.testng.annotations.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class InMemoryPaymentInfoTest {

	private static PaymentInfosService paymentInfosService;
	static Id defaultId;
	static PaymentInfos setupPayment;
	
	@BeforeClass
	public static void setup() {
		paymentInfosService = new PaymentInfosService(new InMemoryPaymentInformationRepository());
		defaultId = paymentInfosService.getNextId();
		setupPayment = PaymentInfos.of(defaultId, Id.of(1), "aa", "aa", "aa");
		paymentInfosService.addPaymentInformation(setupPayment);
		System.out.println(new InMemoryPaymentInformationRepository().getNextId());
	}

	@Test
	public void getNextPaymentInformationId() {
		Id id2 = paymentInfosService.getNextId();
		assertEquals(id2.getId(), defaultId.getId() + 1);
	}
	
	@Test
	public void getPaymentInformation() {
		PaymentInfos paymentInformation = paymentInfosService.getPaymentInformationById(defaultId);
		assertNotNull(paymentInformation);
	}
	
	@Test
	public void addPaymentInformation() {
		Id PaymentInformationId = paymentInfosService.getNextId();
		PaymentInfos newPaymentInformation = PaymentInfos.of(PaymentInformationId,Id.of(1), "bb", "bb", "bb");
		PaymentInfos result = paymentInfosService.addPaymentInformation(newPaymentInformation);
		assertEquals(newPaymentInformation, result);
	}
}
