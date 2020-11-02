package com.asenadev.sana.model.employee;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	@SerializedName("employees")
	private List<EmployeesItem> employees;

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}

	public List<EmployeesItem> getEmployees(){
		return employees;
	}
}