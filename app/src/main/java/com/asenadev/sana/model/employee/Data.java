package com.asenadev.sana.model.employee;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	@SerializedName("employees")
	private List<Employee> employees;

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}

	public List<Employee> getEmployees(){
		return employees;
	}
}