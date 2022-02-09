package ecommercGesture.services;

import java.time.LocalDate;

import ecommercGesture.Dto.MembershipApplication;
import ecommercGesture.additionalClass.ApplicationResultIds;
import ecommercGesture.additionalClass.BillingInformation;
import ecommercGesture.domain.UserMembershipChecker;
import ecommercGesture.exception.InvalidUserException;
import ecommercGesture.exception.PaymentErrorException;
import ecommercGesture.objects.*;

public class MembershipApplicationService {
	
	private final UserService userService;
	private final MemberService memberService;
	private final GlobalPaymentService globalPaymentService;
	public MembershipApplicationService(UserService userService,MemberService memberService, GlobalPaymentService globalPaymentService) {
		this.userService = userService;
		this.memberService = memberService;
		this.globalPaymentService = globalPaymentService;
	}
	
	public ApplicationResultIds applyForMembership(MembershipApplication application) throws Exception {
		LocalDate membershipApplicationDate = LocalDate.now();
		User appliant = userService.getUserById(Id.of(application.getUserId()));
		if(UserMembershipChecker.userHasValidInformationsToApplyMembership(appliant)) {
			if(processToPayment(application.getBilling(),application.getMembershipDetails().getApplictionPrice())) {
				Member newMember = Member.of(memberService.getNextId(),appliant, membershipApplicationDate, getApplicationEndDate(membershipApplicationDate,application.getMembershipDetails().getMembershipDuration()));
				Payment newPayment = Payment.of(globalPaymentService.getNextId(), appliant, application.getMembershipDetails().getApplictionPrice(), membershipApplicationDate);
				Id paymentId = this.globalPaymentService.addPayment(newPayment);
				Id memberId = this.memberService.addMember(newMember);
				return ApplicationResultIds.of(memberId, paymentId);
			}else {
				throw PaymentErrorException.error();
			}
		}else {
			throw InvalidUserException.withId(appliant.getId());
		}
	}
	
	private boolean processToPayment(BillingInformation billing, double transactionPrice) {
		return globalPaymentService.pay(billing,transactionPrice);
	}
	
	public LocalDate getApplicationEndDate(LocalDate chosenDate,int applicationDurationInDay) {
		return chosenDate.plusDays(applicationDurationInDay);
	}
	
}
