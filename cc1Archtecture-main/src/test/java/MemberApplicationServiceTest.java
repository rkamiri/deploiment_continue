package test.java;

import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import ecommercGesture.Dto.MembershipApplication;
import ecommercGesture.additionalClass.*;
import ecommercGesture.defaultRepositoryImplementation.*;
import ecommercGesture.domain.Calendar;
import ecommercGesture.services.*;
import ecommercGesture.objects.*;
import ecommercGesture.repositories.*;


public class MemberApplicationServiceTest {
	
	private static MemberRepository memberRepository;
	private static PaymentRepository paymentRepository;
	private static UserRepository userRepository;

	private static PaymentService paymentService;
	private static ExternalPaymentService externalPaymentService;
	private static GlobalPaymentService globalPaymentService;
	private static UserService userService;
	private static MemberService memberService;
	private static MembershipApplicationService applicationService;
	
	private static Calendar calendar;
	static Id userId;
	
	@BeforeClass
	public static void setup() {
		memberRepository = new InMemoryMemberRepository();
		paymentRepository = new InMemoryPaymentRepository();
		userRepository = new InMemoryUserRepository();
		
		paymentService = new PaymentService(paymentRepository);
		externalPaymentService = new ExternalPaymentService();
		globalPaymentService = new GlobalPaymentService(paymentService, externalPaymentService);
		userService = new UserService(userRepository);
		memberService = new MemberService(memberRepository);
		applicationService = new MembershipApplicationService(userService, memberService, globalPaymentService);
		
		calendar = new SystemCalendar();
		
		User user = User.of(userService.getNextId(), "redha", "chouli", "red", "password");
		userId = userService.addUser(user);
	}

	@Test
	public void memberApplication() throws Exception {
		
		MembershipDetails details = MembershipDetails.of(432.99, 365);
		BillingInformation billing = BillingInformation.of("1545454895432578", "05/25", "458", "chouli redha");
		MembershipApplication application = new MembershipApplication(userId.getId(),details,billing, calendar.currentDate());
		
		final ApplicationResultIds resultIds = applicationService.applyForMembership(application);
		
		final Member storedMember = memberService.getMemberById(resultIds.getMemberId());
		final Payment storedPayment = paymentService.getPaymentById(resultIds.getPaymentId());
		
		Member memberToTest = Member.of(Id.of(1), userService.getUserById(userId), application.getApplicationDate(), applicationService.getApplicationEndDate(application.getApplicationDate(),details.getMembershipDuration()));
		Payment paymentToTest = Payment.of(Id.of(1), userService.getUserById(userId), details.getApplictionPrice(), application.getApplicationDate());
		
		assertEquals(memberToTest, storedMember);
		assertEquals(paymentToTest, storedPayment);
		
	}
	
	@Test
	public void endDate(){
		LocalDate today = calendar.currentDate();
		int duration = 250;
		LocalDate end = applicationService.getApplicationEndDate(today, duration);
		assertEquals(today.plusDays(duration), end);
	}

}
