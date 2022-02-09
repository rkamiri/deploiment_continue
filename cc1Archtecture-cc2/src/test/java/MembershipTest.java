
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Member;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.domain.Calendar;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.Membership;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.repositories.MembershipRepository;
import ecommercGesture.domain.services.MembershipService;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryMembershipRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.SystemCalendar;

public class MembershipTest {

	private static MembershipRepository membershipRepository;
	private static MembershipService membershipService;
	private static Calendar calendar;
	static Id firstId;
	
	@BeforeClass
	public static void setup() {
		membershipRepository = new InMemoryMembershipRepository();
		membershipService = new MembershipService(membershipRepository);
		calendar = new SystemCalendar();
		LocalDate today = calendar.currentDate();
		User user = User.of(Id.of(1), "first", "first", "first", "password");
		firstId = membershipService.getNextId();
		Membership membership = Membership.of(firstId,Id.of(1),5.5, today, today.plusDays(360), true);
		membershipService.addMembership(membership);
	}
	
	@Test
	public void getNextMemberId() {
		Id id1 = membershipService.getNextId();
		Id id2 = membershipService.getNextId();
		assertEquals(true, id2.getId() == id1.getId() + 1);
	}
	
	@Test
	public void getMember() {
		Membership membership = membershipService.getMembershipById(firstId);
		assertEquals(true, membership != null);
	}
	
	@Test
	public void addMember() {
		User user = User.of(Id.of(1), "test", "test", "test", "password");
		LocalDate today = calendar.currentDate();
		Membership newMembership = Membership.of(membershipService.getNextId(), Id.of(1),5.0,today, today.plusDays(600), true);
		Membership membershipresult = membershipService.addMembership(newMembership);
		assertEquals(true, newMembership.equals(membershipresult));
	}
		
	@Test
	public void memberExist() {
		boolean exist = membershipService.isMembershipExist(firstId);
		assertEquals(true, exist);
	}

	@Test
	public void memberNotExist() {
		boolean exist = membershipService.isMembershipExist(Id.of(100000));
		assertEquals(false, exist);
	}

}
