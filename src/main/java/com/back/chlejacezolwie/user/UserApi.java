package com.back.chlejacezolwie.user;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.chlejacezolwie.user.User;
import com.back.chlejacezolwie.dao.Turtle;
import com.back.chlejacezolwie.room.Room;
import com.back.chlejacezolwie.room.RoomParameters;
import com.back.chlejacezolwie.room.RoomRepository;

@RestController
public class UserApi {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoomRepository roomRepository;

	//https://www.innoq.com/en/blog/cookie-based-spring-security-session/
	
	@PostMapping(value = "/join_game")
	public Map<String, String> joinGame(@RequestBody RoomParameters roomParam, HttpSession session) {
		
		//for testing
		/*
		System.out.println(roomParam.getX());
		System.out.println(roomParam.getY());
		System.out.println(roomParam.getZ());
		*/
		
		System.out.println(session.getId());
		
		String sessionId = session.getId();
		
		if(userRepository.findBySession(sessionId).isEmpty()) {
			
			Long time = Instant.now().getEpochSecond();
			
			User newUser = new User(sessionId, time);
			userRepository.save(newUser);
		}
		
		Optional<User> currentUser = userRepository.findBySession(sessionId);
		
		//get id of recently inserted user from database
		HashMap<String, String> userId = new HashMap<String, String>();
		userId.put("client_id", currentUser.get().getId().toString());
		
		//insert new room to database
		ArrayList<Long> playersList = new ArrayList<Long>();
		playersList.add(currentUser.get().getId());
		
		Turtle testTurtle = new Turtle(1, "red");
		Turtle testTurtle2 = new Turtle(2, "blue");
		
		ArrayList<String> stacks = new ArrayList<String>();
		stacks.add(testTurtle.toString());
		stacks.add(testTurtle2.toString());
		
		System.out.println(stacks);
		
		Room newRoom = new Room(playersList, stacks, roomParam.getX(),
				roomParam.getY(), roomParam.getZ(), "none yet");
		
		roomRepository.save(newRoom);
		//end of insert
		
		return userId;
	}
	
}
