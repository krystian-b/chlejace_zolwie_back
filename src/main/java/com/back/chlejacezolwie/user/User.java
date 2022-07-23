package com.back.chlejacezolwie.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {
	
	@Id
	private Long id;
	private String sessionId;
	private Long lastPing;
	private Long roomId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSession() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getLastPing() {
		return lastPing;
	}
	public void setLastPing(Long lastPing) {
		this.lastPing = lastPing;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public User() {

	}
	
	public User(String sessionId, Long lastPing, Long roomId) {
		this.sessionId = sessionId;
		this.lastPing = lastPing;
		this.roomId = roomId;
	}
	
}
