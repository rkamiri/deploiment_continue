package ecommercGesture.domain.services;

import java.time.LocalDate;
import java.util.List;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.repositories.MembershipRepository;
import ecommercGesture.infrastructure.exception.NotMemberException;


public class MembershipService {
	
	private final MembershipRepository memberRepository;
	
	public MembershipService(MembershipRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public Id getNextId() {
		return memberRepository.getNextId();
	}
	
	public Membership getMembershipById(Id id) {
		return memberRepository.getMembershipById(id);
	}
	
	public List<Membership> getAll() {
		return memberRepository.getAll();
	}
	
	public Membership addMembership(Membership member) {
		return memberRepository.saveMembership(member);
	}
	
	public boolean userHaveSuscribedToMembeship(Id userId) {
		return !getMembershipListByUserId(userId).isEmpty();
	}
	
	public List<Membership> getMembershipListByUserId(Id userId) {
		return memberRepository.getMembershipsByUserId(userId);
	}
	
	public Membership getUserLastMembership(Id userId) {
		List<Membership> memberships = getMembershipListByUserId(userId);
		if(memberships.size() > 0) {
			Membership memberResult = memberships.get(0);
			for (int i = 0; i < memberships.size(); i++) {
				Membership membership = memberships.get(i);
				if(membership.getStartMembership().isAfter(memberResult.getStartMembership())) {
					memberResult = membership;
				}
			}
			return memberResult;
		}else {
			throw NotMemberException.withId(userId);
		}
	}
	
	public boolean isCurrentlyMember(Id userId) {
		LocalDate today = LocalDate.now();
		Membership membership = getUserLastMembership(userId);
			return (membership.getStartMembership().isBefore(today) || membership.getStartMembership().isEqual(today) ) && 
					(membership.getEndMembership().isAfter(today) || membership.getEndMembership().isEqual(today) );
	}
	
	public boolean isMembershipExist(Id id) {
		try {
			getMembershipById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
}
