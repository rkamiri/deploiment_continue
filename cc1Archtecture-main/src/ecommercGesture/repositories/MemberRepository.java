package ecommercGesture.repositories;

import java.util.Optional;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.Member;

public interface MemberRepository {
	
	Id getNextId();
	Optional<Member> getMemberById(Id id);
	Id saveMember(Member member);
	
}
