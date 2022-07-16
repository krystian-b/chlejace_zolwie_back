package com.back.chlejacezolwie.user;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	//https://www.innoq.com/en/blog/cookie-based-spring-security-session/
	
	@PostMapping(value = "/join_game")
	public void joinGame(@RequestBody RoomParameters roomParam) {
		
		//for testing
		System.out.println(roomParam.getX());
		System.out.println(roomParam.getY());
		System.out.println(roomParam.getZ());
		
		//System.out.println(session.getId());
		/*
		if(userRepository.findBySession(sessionId).isEmpty()) {
			
			User newUser = new User(sessionId);
			userRepository.save(newUser);
		}
		
		Optional<User> newUser = userRepository.findBySession(sessionId);

		return newUser.get().getId();*/
	}
	
}
