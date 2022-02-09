package com.example.cc1romainkamiri.application.memberCQS.commands;


import com.example.cc1romainkamiri.application.memberCQS.events.ApplyMembershipEvent;
import com.example.cc1romainkamiri.domain.entity.*;
import com.example.cc1romainkamiri.domain.service.PaymentInfosService;
import com.example.cc1romainkamiri.domain.service.MembershipRegistrationService;
import com.example.cc1romainkamiri.exposition.membershipRequests.MembershipRequest;
import com.example.cc1romainkamiri.kernel.CommandHandler;
import com.example.cc1romainkamiri.kernel.Event;
import com.example.cc1romainkamiri.kernel.EventDispatcher;

public class ApplyToMembershipCommandHandler implements CommandHandler<ApplyToMembership, MembershipRequest> {
	
    private final MembershipRegistrationService membershipRegistrationService;
    private final PaymentInfosService paymentInfosService;
    private final EventDispatcher<Event> eventDispatcher;
    
    public ApplyToMembershipCommandHandler(MembershipRegistrationService membershipRegistrationService, PaymentInfosService paymentInfosService, EventDispatcher<Event> eventDispatcher) {
        this.membershipRegistrationService = membershipRegistrationService;
        this.paymentInfosService = paymentInfosService;
        this.eventDispatcher = eventDispatcher;
    }

    public MembershipRequest handle(ApplyToMembership command) {
    	final MembershipRegistration registration = new MembershipRegistration(
    			command.membershipApplyRequest.userId,
    			MembershipInfos.of(command.membershipApplyRequest.infosMembershipRequest.amount, command.membershipApplyRequest.infosMembershipRequest.durationTime),
    			PaymentInfos.of(paymentInfosService.getNextId(), Id.of(command.membershipApplyRequest.userId),command.membershipApplyRequest.paymentInfosRequest.cardNumber, command.membershipApplyRequest.paymentInfosRequest.expirationDate, command.membershipApplyRequest.paymentInfosRequest.secretPictogram),
    			command.membershipApplyRequest.creationDate
    			);
    	Membership membership = membershipRegistrationService.registerForMembership(registration);
    	eventDispatcher.dispatch(ApplyMembershipEvent.of(membership.getId()));
        return MembershipRequest.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(), membership.getStartDateMembership(), membership.getEndDateMembership(), membership.isAutoRenewal());
    }

}
