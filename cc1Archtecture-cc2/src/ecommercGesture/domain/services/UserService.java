package ecommercGesture.domain.services;

import java.util.List;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.repositories.UserRepository;


public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Id getNextId() {
		return userRepository.getNextId();
	}
	
	public User getUserById(Id id) {
		return userRepository.getUserById(id);
	}
	
	public List<User> getAll() {
		return userRepository.getAll();
	}
	
	public User addUser(User user) {
		return userRepository.saveUser(user);
	}
	
	public User ChangeUserName(Id id, String userName) {
		User user = getUserById(id);
		user.changeUserName(userName);
		return userRepository.saveUser(user);
	}
	
	public User changePassword(Id id, String password) {
		User user = getUserById(id);
		user.changePassword(password);
		return userRepository.saveUser(user);
	}
	
	public boolean userExist(Id id) {
		try {
			getUserById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
}
