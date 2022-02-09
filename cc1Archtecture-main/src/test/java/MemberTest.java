package test.java;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.defaultRepositoryImplementation.InMemoryMemberRepository;
import ecommercGesture.defaultRepositoryImplementation.SystemCalendar;
import ecommercGesture.domain.Calendar;
import ecommercGesture.objects.Id;
import ecommercGesture.objects.Member;
import ecommercGesture.objects.User;
import ecommercGesture.repositories.MemberRepository;
import ecommercGesture.services.MemberService;

public class MemberTest {

	private static MemberRepository memberRepository;
	private static MemberService memberService;
	private static Calendar calendar;
	static Id firstId;
	
	@BeforeClass
	public static void setup() {
		memberRepository = new InMemoryMemberRepository();
		memberService = new MemberService(memberRepository);
		calendar = new SystemCalendar();
		LocalDate today = calendar.currentDate();
		User user = User.of(Id.of(1), "first", "first", "first", "password");
		Member member = Member.of(memberService.getNextId(), user, today, today.plusDays(360));
		firstId = memberService.addMember(member);
	}
	
	@Test
	public void getNextMemberId() {
		Id id1 = memberService.getNextId();
		Id id2 = memberService.getNextId();
		assertEquals(true, id2.getId() == id1.getId() + 1);
	}
	
	@Test
	public void getMember() {
		Member member = memberService.getMemberById(firstId);
		assertEquals(true, member != null);
	}
	
	@Test
	public void addMember() {
		User user = User.of(Id.of(1), "test", "test", "test", "password");
		LocalDate today = calendar.currentDate();
		Member newMember = Member.of(memberService.getNextId(), user, today, today.plusDays(600));
		Id resultId = memberService.addMember(newMember);
		Member memberFromService = memberService.getMemberById(resultId);
		assertEquals(true, newMember.equals(memberFromService));
	}
		
	@Test
	public void memberExist() {
		boolean exist = memberService.isMemberExist(firstId);
		assertEquals(true, exist);
	}

	@Test
	public void memberNotExist() {
		boolean exist = memberService.isMemberExist(Id.of(100000));
		assertEquals(false, exist);
	}

}
