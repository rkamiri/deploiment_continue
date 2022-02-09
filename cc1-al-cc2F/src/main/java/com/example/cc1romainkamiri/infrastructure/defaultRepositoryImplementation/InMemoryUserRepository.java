package com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.User;
import com.example.cc1romainkamiri.domain.repository.UserRepository;
import com.example.cc1romainkamiri.infrastructure.exceptions.NotFoundUserException;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepository implements UserRepository {

	private final AtomicInteger count = new AtomicInteger(0);
	private final Map<Id, User> userDbMimic = new HashMap<>();


	@Override
	public Optional<User> findById(Id userId) {
		User user = userDbMimic.get(userId);
		return Optional.ofNullable(user);
	}

	@Override
	public Id addUser(User user) {
		userDbMimic.put(user.getId(), user);
		return user.getId();
	}

	@Override
	public void deleteUser(User user) {
		userDbMimic.remove(user.getId());
	}

	@Override
	public void saveUser(User user) {
		userDbMimic.remove(user.getId());
		userDbMimic.put(user.getId(), user);
	}

	@Override
	public Id getNextId() {
		return Id.of(count.incrementAndGet());
	}

}
