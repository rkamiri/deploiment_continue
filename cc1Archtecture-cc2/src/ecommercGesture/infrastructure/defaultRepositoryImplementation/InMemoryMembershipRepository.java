package ecommercGesture.infrastructure.defaultRepositoryImplementation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.repositories.MembershipRepository;
import kernel.NoSuchEntityException;

@Repository
public class InMemoryMembershipRepository implements MembershipRepository{
	
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<Id, Membership> data = new ConcurrentHashMap<>();
	
	@Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }
	
	@Override
	public Membership getMembershipById(Id id){
		Membership member = data.get(id);
		if (member == null) {
			throw NoSuchEntityException.withIdAndElem(id,"member");
		}
		return member;
	}
	
	@Override
	public List<Membership> getMembershipsByUserId(Id userId){
		List<Membership> result = data.values().stream().filter(membership -> { return membership.getId().equals(userId); }).collect(Collectors.toList());
		return result;
	}
	
	@Override
	public List<Membership> getAll() {
		List<Membership> result = data.values().stream().collect(Collectors.toList());
		return  result;
	}
	
	@Override
	public Membership saveMembership(Membership member) {
		data.put(member.getId(), member);
		return member;
	}
}
