package com.back.chlejacezolwie.dao;

public class RoomCapacity {

	private Long roomId;
	private int maxPlayers;
	private int players;
	private String status;
	
	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public int getPlayers() {
		return players;
	}
	
	public String getStatus() {
		return status;
	}

	
	public boolean compare() {
		if(players < maxPlayers) return true;
		return false;
	}

	public RoomCapacity() {
		
	}
	
	public RoomCapacity(Long roomId, int maxPlayers, String status, int players) {
		this.roomId = roomId;
		this.maxPlayers = maxPlayers;
		this.players = players;
		this.status = status;
	}
	
}
