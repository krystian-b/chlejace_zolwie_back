package com.back.chlejacezolwie.user;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back.chlejacezolwie.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.back.chlejacezolwie.dao.Field;
import com.back.chlejacezolwie.dao.RoomCapacity;
import com.back.chlejacezolwie.dao.RoomCapacityRepository;
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
	
	@Autowired
	RoomCapacityRepository roomCapacityRepository;

	//https://www.innoq.com/en/blog/cookie-based-spring-security-session/
	
	//https://www.javatpoint.com/how-to-convert-string-to-json-object-in-java
	/*
	@GetMapping("/send_room")
	public ArrayList<Field> sendRoom() {
		
		ArrayList<Field> fields = new ArrayList<Field>();
		
		ArrayList<Turtle> turtles = new ArrayList<Turtle>();
		turtles.add(new Turtle(1, "red"));
		turtles.add(new Turtle(2, "blue"));
		
		for(int i = 0; i < 10; i++) {
			fields.add(new Field());
		}
		
		fields.get(0).setTurtles(turtles);
		
		//System.out.println(fields.get(0).toString());
		
		
		String insert = "[ ";

		for(int i = 0; i < fields.size(); i++) {
			insert += fields.get(i).toString();
			if(i < fields.size()-1) insert += ",";
		}
		
		insert += "]";
		
		System.out.println(insert);
		
		Room newRoom = new Room(insert, 5, 5, 10, "not done");
		
		roomRepository.save(newRoom);
		
		return fields;
		
	}*/
	/*
	@GetMapping("/get_room")
	public void getRoom(@RequestParam Long x) {
		
		//https://www.jsonschema2pojo.org/
		
		Room newRoom = roomRepository.findById(x);
		
		String string = newRoom.getStacks();
		
		System.out.println(string);
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Field>>(){}.getType();
		
		ArrayList<Field> fields = 
				gson.fromJson(string, listType);
		
		System.out.println(fields);
	}
	*/
	
	@GetMapping("/are_they_online")
	public void AreTheyOnline() {
		Long time = Instant.now().getEpochSecond();
		time = time - 600; //10 minutes
		userRepository.removeInactiveUsers(time);
	}
	
	@GetMapping("/i_am_online")
	public void iAmOnline(HttpSession session) {
		String sessionId = session.getId();
		updateLastPing(sessionId);
	}
	
	@PostMapping("/join_game")
	public Map<String, String> joinGame(@RequestBody RoomParameters roomParam, HttpSession session) {
		
		String sessionId = session.getId();
		
		if(userRepository.findBySessionId(sessionId).isEmpty()) {
			User newUser = new User(sessionId, null, null);
			userRepository.save(newUser);
		}
		updateLastPing(sessionId);
		
		Optional<User> currentUser = userRepository.findBySessionId(sessionId);
		
		//get id of recently inserted user from database
		HashMap<String, String> userId = new HashMap<String, String>();
		userId.put("client_id", currentUser.get().getId().toString());

		List<RoomCapacity> rooms = 
				roomCapacityRepository.findAll(roomParam.getX(), roomParam.getY(), roomParam.getZ());
		
		int i = 0;
		while(i < rooms.size() && !rooms.get(i).compare()) {i++;}
		
		if(i < rooms.size()) {
			userRepository.updateRoomId(rooms.get(i).getRoomId(), sessionId);
		}
		else {
			createRoom(roomParam);
			userRepository.updateRoomId(roomRepository.findLastRoomId(), sessionId);
		}
		
		return userId;
	}
	
	public void createRoom(RoomParameters roomParam) {
		//create new room
				//generate stack with turtles
				ArrayList<Turtle> turtles = new ArrayList<Turtle>();
				for(int i = 0; i < roomParam.getX(); i++)
				{
					turtles.add(new Turtle(i, "color"));//get colors from color base - todo later
				}
				
				//generate fields
				ArrayList<Field> fields = new ArrayList<Field>();
				for(int i = 0; i <= roomParam.getZ(); i++) {
					fields.add(new Field());
				}
				//populate fields
				fields.get(0).setTurtles(turtles);
				
				//insert new room to database as text
				String insert = "[";

				for(int i = 0; i < fields.size(); i++) {
					insert += fields.get(i).toString();
					if(i < fields.size()-1) insert += ",";
				}
				
				insert += "]";
				
				Room newRoom = new Room(insert, roomParam.getX(),
						roomParam.getY(), roomParam.getZ(), "none yet");
				
				roomRepository.save(newRoom);
				//end of insert
	}
	
	public void updateLastPing(String sessionId) {
		userRepository.updateTime(Instant.now().getEpochSecond(), sessionId);
	}
	
}