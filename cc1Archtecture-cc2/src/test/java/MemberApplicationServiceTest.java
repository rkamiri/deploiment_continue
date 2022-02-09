
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.domain.Calendar;
import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.objects.MembershipApplication;
import ecommercGesture.domain.objects.MembershipDetails;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.repositories.BillingInformationRepository;
import ecommercGesture.domain.repositories.MembershipRepository;
import ecommercGesture.domain.repositories.PaymentRepository;
import ecommercGesture.domain.repositories.UserRepository;
import ecommercGesture.domain.services.BillingInformationService;
import ecommercGesture.domain.services.ExternalPaymentService;
import ecommercGesture.domain.services.GlobalPaymentService;
import ecommercGesture.domain.services.MembershipApplicationService;
import ecommercGesture.domain.services.MembershipService;
import ecommercGesture.domain.services.PaymentService;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryBillingInformationRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryMembershipRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryPaymentRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryUserRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.SystemCalendar;

public class MemberApplicationServiceTest {

	private static MembershipRepository membershipRepository;
	private static PaymentRepository paymentRepository;
	private static UserRepository userRepository;
	private static BillingInformationRepository billingInformationRepository;

	private static PaymentService paymentService;
	private static ExternalPaymentService externalPaymentService;
	private static GlobalPaymentService globalPaymentService;
	private static UserService userService;
	private static MembershipService membershipService;
	private static MembershipApplicationService applicationService;
	private static BillingInformationService billingInformationService;
	
	private static Calendar calendar;
	static Id userId;
	
	@BeforeClass
	public static void setup() {
		membershipRepository = new InMemoryMembershipRepository();
		paymentRepository = new InMemoryPaymentRepository();
		userRepository = new InMemoryUserRepository();
		userRepository = new InMemoryUserRepository();
		billingInformationRepository = new InMemoryBillingInformationRepository();
		
		paymentService = new PaymentService(paymentRepository);
		externalPaymentService = new ExternalPaymentService();
		globalPaymentService = new GlobalPaymentService(paymentService, externalPaymentService);
		userService = new UserService(userRepository);
		membershipService = new MembershipService(membershipRepository);
		billingInformationService = new BillingInformationService(billingInformationRepository);
		applicationService = new MembershipApplicationService(userService, membershipService, globalPaymentService,billingInformationService);
		
		calendar = new SystemCalendar();
		userId = userService.getNextId();
	}

	@Test
	public void memberApplication() throws Exception {
		User first = User.of(Id.of(1), "first", "first", "first", "password");
		userService.addUser(first);
		MembershipDetails details = MembershipDetails.of(432.99, 365);
		BillingInformation billing = BillingInformation.of(Id.of(1),Id.of(1), "1545454895432578" ,"05/25", "458", "chouli redha");
		MembershipApplication application = new MembershipApplication(userId.getId(),details,billing, calendar.currentDate());
		
		final Membership storedMember = applicationService.applyForMembership(application);
				
		Membership memberToTest = Membership.of(Id.of(1), userId,432.99 ,application.getApplicationDate(), applicationService.getApplicationEndDate(application.getApplicationDate(),details.getMembershipDuration()),true);
		
		assertEquals(memberToTest, storedMember);
		
	}
	
	@Test
	public void endDate(){
		LocalDate today = calendar.currentDate();
		int duration = 250;
		LocalDate end = applicationService.getApplicationEndDate(today, duration);
		assertEquals(today.plusDays(duration), end);
	}

}
