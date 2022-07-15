package com.back.chlejacezolwie.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.chlejacezolwie.user.User;
import com.back.chlejacezolwie.room.RoomParameters;

@RestController
public class UserApi {
	
	@Autowired
	UserRepository userRepository;

	//https://spring.io/guides/gs/rest-service-cors/
	@CrossOrigin(origins = "https://chlejace-zolwie-front.herokuapp.com/")
	@PostMapping("/join_game")
	public Long joinGame(@CookieValue("JSESSIONID") String sessionId, 
			@RequestBody RoomParameters roomParam) {
		
		//for testing
		System.out.println(roomParam.getX());
		System.out.println(roomParam.getY());
		System.out.println(roomParam.getZ());
		
		if(userRepository.findBySession(sessionId).isEmpty()) {
			
			User newUser = new User(sessionId);
			userRepository.save(newUser);
		}
		
		Optional<User> newUser = userRepository.findBySession(sessionId);

		return newUser.get().getId();
	}
	
}
