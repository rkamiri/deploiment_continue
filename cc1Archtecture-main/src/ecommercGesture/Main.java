package ecommercGesture;

import java.time.LocalDate;

import ecommercGesture.Dto.MembershipApplication;
import ecommercGesture.additionalClass.BillingInformation;
import ecommercGesture.additionalClass.MembershipDetails;
import ecommercGesture.defaultRepositoryImplementation.InMemoryMemberRepository;
import ecommercGesture.defaultRepositoryImplementation.InMemoryPaymentRepository;
import ecommercGesture.defaultRepositoryImplementation.InMemoryUserRepository;
import ecommercGesture.services.ExternalPaymentService;
import ecommercGesture.objects.User;
import ecommercGesture.repositories.MemberRepository;
import ecommercGesture.repositories.PaymentRepository;
import ecommercGesture.repositories.UserRepository;
import ecommercGesture.services.GlobalPaymentService;
import ecommercGesture.services.MemberService;
import ecommercGesture.services.MembershipApplicationService;
import ecommercGesture.services.PaymentService;
import ecommercGesture.services.UserService;

public class Main {

	public static void main(String[] args) throws Exception {
		
		UserRepository userRepository = new InMemoryUserRepository();
		UserService userService = new UserService(userRepository);
		
		User myUser = User.of(userService.getNextId(), "redha", "chouli", "red", "password");
		MembershipDetails details = MembershipDetails.of(432.99, 365);
		userService.addUser(myUser);
		
		BillingInformation billing = BillingInformation.of("1545454895432578", "05/25", "458", "chouli redha");
		MembershipApplication application = new MembershipApplication(myUser.getId().getId(),details,billing, LocalDate.now() );
		
		MemberRepository memberRepository = new InMemoryMemberRepository();
		PaymentRepository paymentRepository = new InMemoryPaymentRepository();
		
		PaymentService paymentService = new PaymentService(paymentRepository);
		ExternalPaymentService externalPaymentService = new ExternalPaymentService();
		GlobalPaymentService globalPaymentService = new GlobalPaymentService(paymentService, externalPaymentService);
		
		MemberService memberService = new MemberService(memberRepository);
		
		MembershipApplicationService membershipApplicationService = new MembershipApplicationService(userService, memberService,globalPaymentService);
		
		membershipApplicationService.applyForMembership(application);

	}

}
