package com.back.chlejacezolwie.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository <User, Long>{

	//https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#repositories.query-methods
	
	Optional<User> findById(Long id);
	
	Optional<User> findBySession(String session);
	
	List<User> findByLastPingLessThan(Long time);
	
	User save(User user);
	
	//void delete(List<User> users);
	
}
