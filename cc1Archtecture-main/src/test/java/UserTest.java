package test.java;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.defaultRepositoryImplementation.InMemoryUserRepository;
import ecommercGesture.objects.Id;
import ecommercGesture.objects.User;
import ecommercGesture.repositories.UserRepository;
import ecommercGesture.services.UserService;

public class UserTest {
	
	private static UserRepository userRepository;
	private static UserService userService;
	static Id firstId;
	
	@BeforeClass
	public static void setup() {
		userRepository = new InMemoryUserRepository();
		userService = new UserService(userRepository);
		User first = User.of(userService.getNextId(), "first", "first", "first", "password");
		firstId = userService.addUser(first);
	}
	
	@Test
	public void getNextUserId() {
		Id id1 = userService.getNextId();
		Id id2 = userService.getNextId();
		assertEquals(true, id2.getId() == id1.getId() + 1);
	}
	
	@Test
	public void getUser() {
		User user = userService.getUserById(firstId);
		assertEquals(true, user != null);
	}
	
	@Test
	public void addUser() {
		User newUser = User.of(userService.getNextId(), "test", "test", "test", "password");
		Id resultId = userService.addUser(newUser);
		User userFromService = userService.getUserById(resultId);
		assertEquals(true, newUser.equals(userFromService));
	}
	
	@Test
	public void changeUserUsername() {
		userService.ChangeUserName(firstId, "toto");
		User user = userService.getUserById(firstId);
		assertEquals("toto", user.getUserName());		
	}
	
	@Test
	public void changeUserPassword() {
		userService.changePassword(firstId, "tata");
		User user = userService.getUserById(firstId);
		assertEquals("tata", user.getPassword());	
	}
	
	@Test
	public void userExist() {
		boolean exist = userService.userExist(firstId);
		assertEquals(true, exist);
	}

	@Test
	public void userNotExist() {
		boolean exist = userService.userExist(Id.of(100000));
		assertEquals(false, exist);
	}

}
