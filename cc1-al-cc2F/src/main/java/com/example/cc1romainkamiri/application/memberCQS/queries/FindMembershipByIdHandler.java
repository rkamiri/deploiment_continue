package com.example.cc1romainkamiri.application.memberCQS.queries;

import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Membership;
import com.example.cc1romainkamiri.domain.service.MembershipService;
import com.example.cc1romainkamiri.exposition.membershipRequests.MembershipRequest;
import com.example.cc1romainkamiri.kernel.QueryHandler;

public class FindMembershipByIdHandler implements QueryHandler<FindMembershipById, MembershipRequest> {

    private final MembershipService memberService;
	
    public FindMembershipByIdHandler(MembershipService memberService) {
        this.memberService = memberService;
    }

    @Override
    public MembershipRequest handle(FindMembershipById query) {
    	Id memberId = Id.of(query.membershipId);
    	Membership membership = memberService.getMembershipById(memberId);
        return membership != null ? MembershipRequest.of(membership.getId().getId(), membership.getUserId().getId(), membership.getPrice(), membership.getStartDateMembership(), membership.getEndDateMembership(), membership.isAutoRenewal()) : null;
    }
}