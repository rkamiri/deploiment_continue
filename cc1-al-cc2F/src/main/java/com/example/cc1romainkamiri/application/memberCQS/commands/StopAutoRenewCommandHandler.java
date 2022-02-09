package com.example.cc1romainkamiri.application.memberCQS.commands;

import com.example.cc1romainkamiri.application.memberCQS.events.StopAutoRenewEvent;
import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Membership;
import com.example.cc1romainkamiri.domain.service.MembershipRegistrationService;
import com.example.cc1romainkamiri.exposition.membershipRequests.MembershipRequest;
import com.example.cc1romainkamiri.kernel.CommandHandler;
import com.example.cc1romainkamiri.kernel.Event;
import com.example.cc1romainkamiri.kernel.EventDispatcher;

public class StopAutoRenewCommandHandler implements CommandHandler<StopAutoRenew, MembershipRequest> {

    private final MembershipRegistrationService membershipRegistrationService;
    private final EventDispatcher<Event> eventDispatcher;
    
    public StopAutoRenewCommandHandler(MembershipRegistrationService membershipRegistrationService, EventDispatcher<Event> eventDispatcher) {
        this.membershipRegistrationService = membershipRegistrationService;
        this.eventDispatcher = eventDispatcher;
    }
    
    public MembershipRequest handle(StopAutoRenew command) {
    	Membership membership = membershipRegistrationService.stopAutomaticRenew(Id.of(command.userId));
    	eventDispatcher.dispatch(StopAutoRenewEvent.of(membership.getId()));
        return MembershipRequest.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(), membership.getStartDateMembership(), membership.getEndDateMembership(), membership.isAutoRenewal());
    }
}
