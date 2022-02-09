package com.example.cc1romainkamiri.domain.service;

import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Membership;
import com.example.cc1romainkamiri.domain.repository.MembershipRepository;

import java.time.LocalDate;



public class MembershipService {
	
	private final MembershipRepository membershipRepository;
	
	public MembershipService(MembershipRepository membershipRepository) {
		this.membershipRepository = membershipRepository;
	}
	
	public Id getNextId() {
		return membershipRepository.getNextId();
	}
	
	public Membership getMembershipById(Id id) {
		return membershipRepository.getMembershipById(id);
	}
	

	
	public Membership addMembership(Membership membership) {
		return membershipRepository.saveMembership(membership);
	}
	
	public boolean isUserSubscribed(Id userId) {
		return getMembershipByUserId(userId) != null;
	}

	public Membership getMembershipByUserId(Id userId) {
		return membershipRepository.getMembershipByUserId(userId);
	}

	public boolean userAlreadyMember(Id userId) {
		LocalDate today = LocalDate.now();
		Membership membership = this.getLastMembershipByUserId(userId);
		boolean startDateIsBefore = membership.getStartDateMembership().isBefore(today) || membership.getStartDateMembership().isEqual(today);
		boolean endDateIsAfter = (membership.getEndDateMembership().isAfter(today) || membership.getEndDateMembership().isEqual(today));

		return startDateIsBefore && endDateIsAfter;
	}

	public Membership getLastMembershipByUserId(Id userId){
		return this.membershipRepository.getLastMembershipByUserId(userId);
	}

	public void saveMembership(Membership lastMembership) {
		this.membershipRepository.saveMembership(lastMembership);
	}
}
