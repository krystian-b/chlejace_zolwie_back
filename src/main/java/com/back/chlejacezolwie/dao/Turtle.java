package com.back.chlejacezolwie.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Turtle {

	@SerializedName("turtle_id")
	@Expose
	private Integer turtleId;
	
	@SerializedName("color")
	@Expose
	private String color;
	
	public Integer getTurtleId() {
		return turtleId;
	}
	public void setTurtleId(Integer turtleId) {
		this.turtleId = turtleId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Turtle() {
		
	}
	
	public Turtle(Integer turtleId, String color) {
		this.turtleId = turtleId;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "{\"turtle_id\": " + turtleId.toString()
				+ ", \"color\": \"" + color + "\" }";
	}
	
}
