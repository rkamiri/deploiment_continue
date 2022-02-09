package ecommercGesture.domain.repositories;

import java.util.List;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;

public interface UserRepository {

	Id getNextId();
	User getUserById(Id id);
	List<User> getAll();
	User saveUser(User user);
	
}
