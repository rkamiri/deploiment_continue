package ecommercGesture.services;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.User;
import ecommercGesture.repositories.UserRepository;

public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Id getNextId() {
		return userRepository.getNextId();
	}
	
	public User getUserById(Id id) {
		return userRepository.getUserById(id).get();
	}
	
	public Id addUser(User user) {
		return userRepository.saveUser(user);
	}
	
	public void ChangeUserName(Id id, String userName) {
		User user = getUserById(id);
		user.changeUserName(userName);
		userRepository.saveUser(user);
	}
	
	public void changePassword(Id id, String password) {
		User user = getUserById(id);
		user.changePassword(password);
		userRepository.saveUser(user);
	}
	
	public boolean userExist(Id id) {
		return userRepository.getUserById(id).isPresent();
	}
	
}
