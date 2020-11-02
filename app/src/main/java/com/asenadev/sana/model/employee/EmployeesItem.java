package com.asenadev.sana.model.employee;

import com.google.gson.annotations.SerializedName;

public class EmployeesItem{

	@SerializedName("post")
	private String post;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("username")
	private String username;

	public String getPost(){
		return post;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getUsername(){
		return username;
	}
}