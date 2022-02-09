package test.java;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.defaultRepositoryImplementation.InMemoryPaymentRepository;
import ecommercGesture.defaultRepositoryImplementation.SystemCalendar;
import ecommercGesture.domain.Calendar;
import ecommercGesture.objects.Id;
import ecommercGesture.objects.Payment;
import ecommercGesture.objects.User;
import ecommercGesture.repositories.PaymentRepository;
import ecommercGesture.services.PaymentService;

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
		User user = User.of(Id.of(1), "first", "first", "first", "password");
		Payment first = Payment.of(paymentService.getNextId(),user,300.5,calendar.currentDate());
		firstId = paymentService.addPayment(first);
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
		User user = User.of(Id.of(1), "test", "test", "test", "password");
		Payment newPayment = Payment.of(paymentService.getNextId(),user,350.5,calendar.currentDate());
		Id resultId = paymentService.addPayment(newPayment);
		Payment paymentFromService = paymentService.getPaymentById(resultId);
		assertEquals(true, newPayment.equals(paymentFromService));
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
