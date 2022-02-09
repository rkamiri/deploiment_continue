
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.repositories.UserRepository;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryUserRepository;


public class UserTest {
	
	private static UserRepository userRepository;
	private static UserService userService;
	static Id firstId;
	static User first;
	
	@BeforeClass
	public static void setup() {
		userRepository = new InMemoryUserRepository();
		userService = new UserService(userRepository);
		firstId = userService.getNextId();
		first = User.of(firstId, "first", "first", "first", "password");
		userService.addUser(first);
		
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
	public void getAllUser() {
		Id userId = userService.getNextId();
		User newUser = User.of(userId, "test", "test", "test", "password");
		userService.addUser(newUser);
		List<User> userList = new ArrayList<User>();
		userList.add(first);
		userList.add(newUser);
		List<User> result = userService.getAll();
		assertEquals(true, result.get(0).equals(first));
	}
	
	@Test
	public void addUser() {
		Id userId = userService.getNextId();
		User newUser = User.of(userId, "test", "test", "test", "password");
		userService.addUser(newUser);
		User userFromService = userService.getUserById(userId);
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
