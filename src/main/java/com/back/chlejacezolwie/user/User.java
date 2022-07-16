package com.back.chlejacezolwie.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Users")
public class User {
	
	@Id
	private Long id;
	private String session;
	private Long lastPing;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Long getLastPing() {
		return lastPing;
	}
	public void setLastPing(Long lastPing) {
		this.lastPing = lastPing;
	}
	
	public User() {

	}
	
	public User(String session, Long lastPing) {
		this.session = session;
		this.lastPing = lastPing;
	}
	
}
