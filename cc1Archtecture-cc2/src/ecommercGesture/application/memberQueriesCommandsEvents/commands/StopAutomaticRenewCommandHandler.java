package ecommercGesture.application.memberQueriesCommandsEvents.commands;

import ecommercGesture.application.memberQueriesCommandsEvents.events.StopAutomaticRenewEvent;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.services.MembershipApplicationService;
import ecommercGesture.exposition.memberDTO.MembershipDTO;
import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;

public class StopAutomaticRenewCommandHandler implements CommandHandler<StopAutomaticRenew, MembershipDTO>{

    private final MembershipApplicationService membershipApplicationService;
    private final EventDispatcher<Event> eventDispatcher;
    
    public StopAutomaticRenewCommandHandler(MembershipApplicationService membershipApplicationService, EventDispatcher<Event> eventDispatcher) {
        this.membershipApplicationService = membershipApplicationService;
        this.eventDispatcher = eventDispatcher;
    }
    
    public MembershipDTO handle(StopAutomaticRenew command) {
    	Membership membership = membershipApplicationService.stopAutomaticRenew(Id.of(command.userId));
    	eventDispatcher.dispatch(StopAutomaticRenewEvent.of(membership.getId()));
        MembershipDTO membershipResponseResult = MembershipDTO.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(), membership.getStartMembership(), membership.getEndMembership(), membership.isAutomaticMembershipRenew());
    	return membershipResponseResult;
    }
}
