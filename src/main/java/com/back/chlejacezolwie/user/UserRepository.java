package com.back.chlejacezolwie.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository <User, Long>{

	//https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#repositories.query-methods
	
	Optional<User> findById(Long id);
	
	Optional<User> findBySessionId(String sessionId);
	
	List<User> findByLastPingLessThan(Long time);
	
	User save(User user);
	
	@Modifying
	@Query("UPDATE users SET last_ping = :time WHERE session_id = :sessionId")
	void updateTime(Long time, String sessionId);
	
	@Modifying
	@Query("UPDATE users SET room_id = :roomId WHERE session_id = :sessionId")
	void updateRoomId(Long roomId, String sessionId);
	
	@Modifying
	@Query("DELETE FROM users WHERE last_ping < :timeout")
	void removeInactiveUsers(Long timeout);
	
}
