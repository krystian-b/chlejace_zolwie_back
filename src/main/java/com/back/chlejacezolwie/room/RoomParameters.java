package com.back.chlejacezolwie.room;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomParameters {

	@JsonProperty("X")
	private int x;
	
	@JsonProperty("Y")
	private int y;
	
	@JsonProperty("Z")
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
	
	public RoomParameters(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	

	
	
	
	
}
