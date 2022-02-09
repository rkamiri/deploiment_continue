package ecommercGesture.services;

import java.time.LocalDate;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.Member;
import ecommercGesture.repositories.MemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public Id getNextId() {
		return memberRepository.getNextId();
	}
	
	public Member getMemberById(Id id) {
		return memberRepository.getMemberById(id).get();
	}
	
	public Id addMember(Member member) {
		return memberRepository.saveMember(member);
	}
	
	public boolean isMemberExist(Id id) {
		return memberRepository.getMemberById(id).isPresent();
	}
	
	public boolean isCurrentlyMember(Id id) {
		LocalDate today = LocalDate.now();
		Member member = getMemberById(id);
			return (member.getStartMembership().isBefore(today) || member.getStartMembership().isEqual(today) ) && 
					(member.getEndMembership().isAfter(today) || member.getEndMembership().isEqual(today) );
	}
	
}
