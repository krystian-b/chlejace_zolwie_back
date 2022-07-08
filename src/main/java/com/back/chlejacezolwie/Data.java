package com.back.chlejacezolwie;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("test")
public class Data {

	@Id
	private Long id;
	
	private int number;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Data() {
		
	}
	
	public Data(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
}
