package com.back.chlejacezolwie.user;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository <User, Long>{

	Optional<User> findById(Long id);
	
	Optional<User> findBySession(String session);
	
	User save(User user);
	
}
