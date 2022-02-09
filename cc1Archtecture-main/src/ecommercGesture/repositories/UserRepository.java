package ecommercGesture.repositories;

import java.util.Optional;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.User;

public interface UserRepository {

	Id getNextId();
	Optional<User> getUserById(Id id);
	Id saveUser(User user);
	
}
