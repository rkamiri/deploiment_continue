package com.example.cc1romainkamiri.domain.repository;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Membership;

public interface MembershipRepository {
	
	Id getNextId();
	Membership getMembershipById(Id id);
	Membership getMembershipByUserId(Id id);
	Membership saveMembership(Membership member);
	Membership getLastMembershipByUserId(Id userId);
}
