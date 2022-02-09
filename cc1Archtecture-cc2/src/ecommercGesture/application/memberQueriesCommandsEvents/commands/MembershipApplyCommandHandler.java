package ecommercGesture.application.memberQueriesCommandsEvents.commands;

import ecommercGesture.application.memberQueriesCommandsEvents.events.MembershipApplyEvent;
import ecommercGesture.domain.objects.BillingInformation;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.objects.MembershipApplication;
import ecommercGesture.domain.objects.MembershipDetails;
import ecommercGesture.domain.services.BillingInformationService;
import ecommercGesture.domain.services.MembershipApplicationService;
import ecommercGesture.exposition.memberDTO.MembershipDTO;
import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;

public class MembershipApplyCommandHandler implements CommandHandler<MembershipApply, MembershipDTO>{
	
    private final MembershipApplicationService membershipApplicationService;
    private final BillingInformationService billingInformationService;
    private final EventDispatcher<Event> eventDispatcher;
    
    public MembershipApplyCommandHandler(MembershipApplicationService membershipApplicationService, BillingInformationService billingInformationService, EventDispatcher<Event> eventDispatcher) {
        this.membershipApplicationService = membershipApplicationService;
        this.billingInformationService = billingInformationService;
        this.eventDispatcher = eventDispatcher;
    }

    public MembershipDTO handle(MembershipApply command) {
    	final MembershipApplication application = new MembershipApplication(
    			command.membershipApplyDTO.userId,
    			MembershipDetails.of(command.membershipApplyDTO.membershipDetailsRequest.applictionPrice, command.membershipApplyDTO.membershipDetailsRequest.membershipDuration),
    			BillingInformation.of(billingInformationService.getNextId(),Id.of(command.membershipApplyDTO.userId),command.membershipApplyDTO.billingInformationsRequest.cardNumber, command.membershipApplyDTO.billingInformationsRequest.expirationDate, command.membershipApplyDTO.billingInformationsRequest.secretPictogram, command.membershipApplyDTO.billingInformationsRequest.ownerName),
    			command.membershipApplyDTO.applicationDate
    			);
    	Membership membership = membershipApplicationService.applyForMembership(application);
    	eventDispatcher.dispatch(MembershipApplyEvent.of(membership.getId()));
        MembershipDTO membershipResponseResult = MembershipDTO.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(), membership.getStartMembership(), membership.getEndMembership(), membership.isAutomaticMembershipRenew());
    	return membershipResponseResult;
    }

}
