package ecommercGesture.application.memberQueriesCommandsEvents.queries;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.services.MembershipService;
import ecommercGesture.exposition.memberDTO.MembershipDTO;
import kernel.QueryHandler;

public class RetrieveMembershipByIdHandler implements QueryHandler<RetrieveMembershipById, MembershipDTO>{

    private final MembershipService memberService;
	
    public RetrieveMembershipByIdHandler(MembershipService memberService) {
        this.memberService = memberService;
    }

    @Override
    public MembershipDTO handle(RetrieveMembershipById query) {
    	Id memberId = Id.of(query.memberId);
    	Membership membership = memberService.getMembershipById(memberId);
        MembershipDTO membershipResponseResult = MembershipDTO.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(), membership.getStartMembership(), membership.getEndMembership(), membership.isAutomaticMembershipRenew());
        return membershipResponseResult;
    }
}