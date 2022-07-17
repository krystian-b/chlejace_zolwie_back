package com.back.chlejacezolwie.dao;

public class Turtle {

	private Integer turtleId;
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
		return "{turtle_id:" + turtleId.toString()
				+ ",color:\"" + color + "\"}";
	}
	
}
