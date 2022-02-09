package ecommercGesture.application.memberQueriesCommandsEvents.queries;

import java.util.List;
import java.util.stream.Collectors;

import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.services.MembershipService;
import ecommercGesture.exposition.memberDTO.MembershipDTO;
import ecommercGesture.exposition.memberDTO.MembershipsDTO;
import kernel.QueryHandler;

public class RetrieveMembershipsHandler implements QueryHandler<RetrieveMemberships, MembershipsDTO> {

    private final MembershipService memberService;

    public RetrieveMembershipsHandler(MembershipService memberService) {
        this.memberService = memberService;
    }
    
    @Override
    public MembershipsDTO handle(RetrieveMemberships query) {
    	List<Membership> memberships = memberService.getAll();
    	MembershipsDTO membershipsResponseResult = MembershipsDTO.of(memberships.stream()
        		.map(membership -> 
        			MembershipDTO.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(),
        			membership.getStartMembership(),membership.getEndMembership(), membership.isAutomaticMembershipRenew())).collect(Collectors.toList()));
        return membershipsResponseResult;
    }
}
