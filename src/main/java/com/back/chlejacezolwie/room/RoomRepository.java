package com.back.chlejacezolwie.room;

import org.springframework.data.repository.Repository;

public interface RoomRepository extends Repository <Room, Long> {
	
	Room save(Room newRoom);
	
}
