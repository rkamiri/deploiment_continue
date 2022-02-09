package com.example.cc1romainkamiri;

import static org.junit.Assert.assertTrue;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.User;
import com.example.cc1romainkamiri.domain.repository.UserRepository;
import com.example.cc1romainkamiri.domain.service.UserService;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryUserRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;


public class UserTest {
	
	private static UserRepository userRepository;
	private static UserService userService;
	static Id beginId;
	static User beginUser;
	
	@BeforeClass
	public static void setup() {
		userService = new UserService(new InMemoryUserRepository());
		beginId = userService.getNextId();
		beginUser = User.of(beginId, "aa", "aa", "aa", "aa");
		userService.addUser(beginUser);
		
	}

	@Test
	public void getNextUserId() {
		Id nextId = userService.getNextId();
        assertTrue(nextId.getId() == beginUser.getId().getId() + 1);
	}
	
	@Test
	public void getUser() {
		Optional<User> user= userService.getUser(beginId);
        assertTrue(user.isPresent());
	}

	
	@Test
	public void addUser() {
		Id userId = userService.getNextId();
		User newUser = User.of(userId, "aa", "aa", "aa", "aa");
		userService.addUser(newUser);
		Optional<User> user = userService.getUser(userId);
		assertTrue(user.get().getId().getId() == userService.getNextId().getId()-1);
	}


}
