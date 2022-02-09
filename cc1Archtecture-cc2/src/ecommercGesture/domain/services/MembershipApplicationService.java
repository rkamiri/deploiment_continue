package ecommercGesture.domain.services;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import ecommercGesture.domain.UserMembershipChecker;
import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.objects.MembershipApplication;
import ecommercGesture.domain.objects.MembershipDetails;
import ecommercGesture.domain.objects.Payment;
import ecommercGesture.domain.objects.User;
import ecommercGesture.infrastructure.exception.InvalidMembershipDurationException;
import ecommercGesture.infrastructure.exception.InvalidUserException;
import ecommercGesture.infrastructure.exception.IsAlreadyMemberException;
import ecommercGesture.infrastructure.exception.IsCurrentlyMemberException;
import ecommercGesture.infrastructure.exception.IsNotMemberException;
import ecommercGesture.infrastructure.exception.NegativePriceException;
import ecommercGesture.infrastructure.exception.PaymentErrorException;
import ecommercGesture.infrastructure.exception.StopRenwCurrentlyNotMemberException;


public class MembershipApplicationService {

	private final UserService userService;
	private final MembershipService membershipService;
	private final GlobalPaymentService globalPaymentService;
	private final BillingInformationService billingInformationService;

	public MembershipApplicationService(UserService userService,MembershipService membershipService, GlobalPaymentService globalPaymentService, BillingInformationService billingInformationService) {
		this.userService = userService;
		this.membershipService = membershipService;
		this.globalPaymentService = globalPaymentService;
		this.billingInformationService = billingInformationService;
	}

	private Membership apply(MembershipApplication application,Id userId, boolean firstApplication) {
		LocalDate membershipApplicationDate = LocalDate.now();
		if(processToPayment(application.getBilling(),application.getMembershipDetails().getApplictionPrice())) {
			if(firstApplication) {
				billingInformationService.addBillingInformation(application.getBilling());
			}
			Membership newMembership = Membership.of(membershipService.getNextId(),userId, application.getMembershipDetails().getApplictionPrice() ,membershipApplicationDate, getApplicationEndDate(membershipApplicationDate,application.getMembershipDetails().getMembershipDuration()), true);
			Payment.of(globalPaymentService.getNextId(), userId, application.getMembershipDetails().getApplictionPrice(), membershipApplicationDate);
			Membership membership = this.membershipService.addMembership(newMembership);
			return membership;
		}else {
			throw PaymentErrorException.error();
		}
	}

	public Membership applyForMembership(MembershipApplication application){
		checkApplicationDetail(application);
		User appliant = userService.getUserById(Id.of(application.getUserId()));
		if(!this.membershipService.userHaveSuscribedToMembeship(appliant.getId())) {
			if(UserMembershipChecker.userHasValidInformationsToApplyMembership(appliant)) {
				return apply(application,appliant.getId(), true);
			}else {
				throw InvalidUserException.withId(appliant.getId());
			}
		}else {
			throw IsAlreadyMemberException.withId(appliant.getId());
		}
	}

	public Membership renewMembership(Id userId) {
		if(this.membershipService.userHaveSuscribedToMembeship(userId)) {
			Membership lastMembersip = this.membershipService.getUserLastMembership(userId);
			if(!this.membershipService.isCurrentlyMember(userId)) {
				MembershipApplication application = new MembershipApplication(
						userId.getId(),
						MembershipDetails.of(lastMembersip.getPrice(), getMembershipDuration(lastMembersip)),
						billingInformationService.getbillingInformationByUserId(userId),
						LocalDate.now());
				return apply(application, userId, false);
			}else {
				throw IsCurrentlyMemberException.withId(userId,lastMembersip.getEndMembership().toString());
			}
		}else {
			throw IsNotMemberException.withId(userId);
		}
	}

	@Scheduled(cron = "0 0 12 * * ?")
	private void automaticMembershipRenew() {
		List<User> users = this.userService.getAll();
		users.forEach(user -> {
			Id userId = user.getId();
			if(this.membershipService.userHaveSuscribedToMembeship(userId)) {
				if(!this.membershipService.isCurrentlyMember(userId)) {
					if(this.membershipService.getUserLastMembership(userId).isAutomaticMembershipRenew() == true) {
						renewMembership(userId);
					}
				}
			}
		});
	}

	public Membership stopAutomaticRenew(Id userId) {
		if(this.membershipService.userHaveSuscribedToMembeship(userId)) {
			if(this.membershipService.isCurrentlyMember(userId)) {
				Membership lastMembership = this.membershipService.getUserLastMembership(userId);
				lastMembership.setAutomaticMembershipRenew(false);
				return this.membershipService.addMembership(lastMembership);
			}else {
				throw StopRenwCurrentlyNotMemberException.error();
			}
		}else {
			throw IsNotMemberException.withId(userId);
		}
	}

	private void checkApplicationDetail(MembershipApplication application) {
		int day = application.getMembershipDetails().getMembershipDuration();
		double price = application.getMembershipDetails().getApplictionPrice();
		if(day < 1) {
			throw InvalidMembershipDurationException.withDay(day);
		}
		if(price < 0) {
			throw NegativePriceException.withPrice(price);
		}
	}

	private boolean processToPayment(BillingInformation billing, double transactionPrice) {
		return globalPaymentService.pay(billing,transactionPrice);
	}

	public LocalDate getApplicationEndDate(LocalDate chosenDate,int applicationDurationInDay) {
		return chosenDate.plusDays(applicationDurationInDay);
	}

	public int getMembershipDuration(Membership membership) {
		return (int) Duration.between(membership.getStartMembership().atStartOfDay(), membership.getEndMembership().atStartOfDay()).toDays();
	}

}
