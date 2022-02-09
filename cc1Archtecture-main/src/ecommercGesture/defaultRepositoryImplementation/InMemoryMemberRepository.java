package ecommercGesture.defaultRepositoryImplementation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ecommercGesture.objects.Member;
import ecommercGesture.objects.Id;
import ecommercGesture.repositories.MemberRepository;

public class InMemoryMemberRepository implements MemberRepository{
	
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<Id, Member> data = new ConcurrentHashMap<>();
	
	@Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }
	
	@Override
	public Optional<Member> getMemberById(Id id){
		return Optional.ofNullable(data.get(id));
	}
	
	@Override
	public Id saveMember(Member member) {
		data.put(member.getId(), member);
		return member.getId();
	}
}
