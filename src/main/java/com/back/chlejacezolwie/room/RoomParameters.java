package com.back.chlejacezolwie.room;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomParameters {

	@JsonProperty("X")//max_players
	private int x;
	
	@JsonProperty("Y")//max_cards
	private int y;
	
	@JsonProperty("Z")//length
	private int z;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	public RoomParameters() {

	}
	
	public RoomParameters(@JsonProperty("X") int x, 
			@JsonProperty("Y") int y, @JsonProperty("Z") int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	

	
	
	
	
}
