package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.domain.Calendar;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Payment;
import ecommercGesture.domain.repositories.PaymentRepository;
import ecommercGesture.domain.services.PaymentService;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryPaymentRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.SystemCalendar;

public class PaymentTest {

	private static PaymentRepository paymentRepository;
	private static PaymentService paymentService;
	private static Calendar calendar;
	static Id firstId;
	
	@BeforeClass
	public static void setup() {
		paymentRepository = new InMemoryPaymentRepository();
		paymentService = new PaymentService(paymentRepository);
		calendar = new SystemCalendar();
		firstId = paymentService.getNextId();
		Payment first = Payment.of(firstId,Id.of(1),300.5,calendar.currentDate());
		paymentService.addPayment(first);
	}
	
	@Test
	public void getNextPaymentId() {
		Id id1 = paymentService.getNextId();
		Id id2 = paymentService.getNextId();
		assertEquals(true, id2.getId() == id1.getId() + 1);
	}
	
	@Test
	public void getPayment() {
		Payment payment = paymentService.getPaymentById(firstId);
		assertEquals(true, payment != null);
	}
	
	@Test
	public void addPayment() {
		Payment newPayment = Payment.of(paymentService.getNextId(),Id.of(1),350.5,calendar.currentDate());
		Payment paymentResult = paymentService.addPayment(newPayment);
		assertEquals(true, newPayment.equals(paymentResult));
	}
		
	@Test
	public void paymentExist() {
		boolean exist = paymentService.paymentExist(firstId);
		assertEquals(true, exist);
	}

	@Test
	public void paymentNotExist() {
		boolean exist = paymentService.paymentExist(Id.of(100000));
		assertEquals(false, exist);
	}

}
