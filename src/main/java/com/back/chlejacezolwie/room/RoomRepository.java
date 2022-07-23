package com.back.chlejacezolwie.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface RoomRepository extends Repository <Room, Long> {
	
	@Query("SELECT stacks FROM room WHERE id = :id")
	String getStacks(Long id);
	
	@Query("SELECT id FROM Room ORDER BY id DESC LIMIT 1")
	Long findLastRoomId();
	
	Room findById(Long id);
	
	Room save(Room newRoom);
	
}
