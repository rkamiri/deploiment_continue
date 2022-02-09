package ecommercGesture.defaultRepositoryImplementation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ecommercGesture.objects.Id;
import ecommercGesture.objects.User;
import ecommercGesture.repositories.UserRepository;

public class InMemoryUserRepository implements UserRepository{

	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, User> data = new ConcurrentHashMap<>();
	
	@Override
	public Id getNextId() {
		return Id.of(count.incrementAndGet());
	}

	@Override
	public Optional<User> getUserById(Id id) {
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public Id saveUser(User user) {
		data.put(user.getId(), user);
		return user.getId();
	}

}
