package ecommercGesture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

		/*
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
		*/

	}

}
