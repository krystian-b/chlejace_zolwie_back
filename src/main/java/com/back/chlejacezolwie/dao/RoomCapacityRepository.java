package com.back.chlejacezolwie.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface RoomCapacityRepository extends Repository <RoomCapacity, Long> {

	@Query("SELECT users.room_id, room.max_players, room.status, count(*) AS players "
			+ "FROM users JOIN room ON users.room_id = room.id "
			+ "GROUP BY users.room_id, room.max_players, room.status")
	List<RoomCapacity> findAll();
}
