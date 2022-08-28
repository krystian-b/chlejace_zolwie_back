package com.back.chlejacezolwie.user;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
	public Map<String, String> AreTheyOnline() {
		Long time = Instant.now().getEpochSecond();
		time = time - 600; //10 minutes
		userRepository.removeInactiveUsers(time);
		
		return new HashMap<String, String>();
	}
	
	@GetMapping("/i_am_online")
	public Map<String, String> iAmOnline(HttpSession session) {
		
		String sessionId = session.getId();
		
		if(userRepository.findBySessionId(sessionId).isEmpty()) {
			User newUser = new User(sessionId, null, null);// wysłać błąd jak nie ma w bazie
			userRepository.save(newUser);
		}
		updateLastPing(sessionId);
		
		return new HashMap<String, String>();
	}
	
	@PostMapping("/join_game")
	public Map<String, String> joinGame(@RequestBody RoomParameters roomParam,
			HttpServletRequest request) {

		//https://docs.spring.io/spring-session/docs/2.5.0/reference/html5/index.html
		
		//https://www.springcloud.io/post/2022-04/spring-samesite/#gsc.tab=0
		//https://www.javainuse.com/spring/springboot_session
		//https://www.javadevjournal.com/spring/spring-session-with-jdbc/
		//https://stackoverflow.com/questions/52978170/spring-session-is-not-creating-tables-spring-session-and-spring-session-attribut

		HttpSession session = request.getSession();

		String sessionId = request.getSession().getId();
		
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