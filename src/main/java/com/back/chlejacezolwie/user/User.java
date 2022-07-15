package com.back.chlejacezolwie.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Users")
public class User {
	
	@Id
	private Long id;
	private String session;
	
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
	
	public User() {

	}
	
	public User(String session) {
		this.session = session;
	}
	
}
