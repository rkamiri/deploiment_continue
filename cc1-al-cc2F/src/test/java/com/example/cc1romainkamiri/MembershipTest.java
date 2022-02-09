
package com.example.cc1romainkamiri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.Membership;
import com.example.cc1romainkamiri.domain.entity.User;
import com.example.cc1romainkamiri.domain.repository.MembershipRepository;
import com.example.cc1romainkamiri.domain.service.MembershipService;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryMembershipRepository;
import org.junit.BeforeClass;
import org.junit.Test;

public class MembershipTest {

	private static MembershipRepository membershipRepository;
	private static MembershipService membershipService;
	static Id beginId;
	
	@BeforeClass
	public static void setup() {
		membershipRepository = new InMemoryMembershipRepository();
		membershipService = new MembershipService(membershipRepository);
		LocalDate today = LocalDate.now();
		beginId = membershipService.getNextId();
		Membership membership = Membership.of(beginId,Id.of(1),15.99, today, today.plusDays(31), true);
		membershipService.addMembership(membership);
	}

	@Test
	public void getMember() {
        assertTrue(membershipService.getMembershipById(beginId).getId().getId() == beginId.getId());
	}
	
	@Test
	public void addMember() {
		LocalDate today = LocalDate.now();
		Membership newMembership = Membership.of(membershipService.getNextId(), Id.of(1),15.99,today, today.plusDays(30), true);
        assertTrue(membershipService.addMembership(newMembership).getId().getId() == beginId.getId() + 1);
	}

    @Test
    public void getNextMemberId() {
        Id id2 = membershipService.getNextId();
        System.out.println(id2.getId());
        assertTrue(id2.getId() == beginId.getId() + 2);
    }
}
