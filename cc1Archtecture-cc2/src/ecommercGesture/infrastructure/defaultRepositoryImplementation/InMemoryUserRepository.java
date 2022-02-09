package ecommercGesture.infrastructure.defaultRepositoryImplementation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.repositories.UserRepository;
import kernel.NoSuchEntityException;

@Repository
public class InMemoryUserRepository implements UserRepository{

	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, User> data = new ConcurrentHashMap<>();
	
	@Override
	public Id getNextId() {
		return Id.of(count.incrementAndGet());
	}

	@Override
	public User getUserById(Id id) {
		User user = data.get(id);
		if (user == null) {
			throw NoSuchEntityException.withIdAndElem(id,"user");
		}
		return user;
	}
	
	@Override
	public List<User> getAll() {
		List<User> result = data.values().stream().collect(Collectors.toList());
		return  result;
	}

	@Override
	public User saveUser(User user) {
		data.put(user.getId(), user);
		return user;
	}

}
