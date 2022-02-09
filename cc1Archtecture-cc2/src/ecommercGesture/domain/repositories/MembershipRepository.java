package ecommercGesture.domain.repositories;


import java.util.List;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;

public interface MembershipRepository {
	
	Id getNextId();
	Membership getMembershipById(Id id);
	List<Membership> getAll();
	Membership saveMembership(Membership member);
	List<Membership> getMembershipsByUserId(Id userId);
	
}
