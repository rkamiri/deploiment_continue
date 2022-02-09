package com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Membership;
import com.example.cc1romainkamiri.domain.entity.User;
import com.example.cc1romainkamiri.domain.repository.MembershipRepository;
import com.example.cc1romainkamiri.kernel.NoSuchEntityException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMembershipRepository implements MembershipRepository {
	
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<Id, Membership> membershipDbMimic = new HashMap<>();

    @Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }

    @Override
    public Membership getMembershipById(Id id) {
        return membershipDbMimic.get(id);
    }

    @Override
    public Membership getMembershipByUserId(Id id) {
        return this.getLastMembershipByUserId(id);
    }

    @Override
    public Membership saveMembership(Membership membership) {
        if(membershipDbMimic.get(membership.getId()) != null){
            membershipDbMimic.remove(membership.getId());
        }
        membershipDbMimic.put(membership.getId(), membership);
        return membership;
    }

    @Override
    public Membership getLastMembershipByUserId(Id userId) {
        List<Membership> memberships = membershipDbMimic.values().stream().filter(membership -> membership.getId().getId() == (userId.getId())).collect(Collectors.toList());
        return memberships.size() > 0 ? memberships.get(memberships.size()-1) : null;
    }
}
