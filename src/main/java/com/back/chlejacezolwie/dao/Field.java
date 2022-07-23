package com.back.chlejacezolwie.dao;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field {

	@SerializedName("turtles")
	@Expose
	private ArrayList<Turtle> turtles;

	public ArrayList<Turtle> getTurtles() {
		return turtles;
	}

	public void setTurtles(ArrayList<Turtle> turtles) {
		this.turtles = turtles;
	}
	
	public Field() {
		this.turtles = new ArrayList<Turtle>();
	}

	public Field(ArrayList<Turtle> turtles) {
		this.turtles = turtles;
	}
	
	@Override
	public String toString() {
		return "{\"turtles\": " + turtles.toString() + "}";
	}
}
