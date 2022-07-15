package com.back.chlejacezolwie.room;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Room")
public class Room {

	@Id
	private Long id;
	private List<Long> players;
	private List<String> stacks;
	private int maxPlayers;
	private int maxCards;
	private int length;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getPlayers() {
		return players;
	}
	public void setPlayers(List<Long> players) {
		this.players = players;
	}
	public List<String> getStacks() {
		return stacks;
	}
	public void setStacks(List<String> stacks) {
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
	
	public Room() {
		
	}
	
	public Room(List<Long> players, List<String> stacks, int maxPlayers, int maxCards, int length) {
		this.players = players;
		this.stacks = stacks;
		this.maxPlayers = maxPlayers;
		this.maxCards = maxCards;
		this.length = length;
	}
	
}
