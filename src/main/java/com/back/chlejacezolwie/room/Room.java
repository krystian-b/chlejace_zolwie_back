package com.back.chlejacezolwie.room;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("room")
public class Room {

	@Id
	private Long id;
	private String stacks;
	private int maxPlayers;
	private int maxCards;
	private int length;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStacks() {
		return stacks;
	}
	public void setStacks(String stacks) {
		this.stacks = stacks;
	}
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public int getMaxCards() {
		return maxCards;
	}
	public void setMaxCards(int maxCards) {
		this.maxCards = maxCards;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Room() {
		
	}
	
	public Room(String stacks, int maxPlayers, 
			int maxCards, int length, String status) {
		this.stacks = stacks;
		this.maxPlayers = maxPlayers;
		this.maxCards = maxCards;
		this.length = length;
		this.status = status;
	}
	
}
