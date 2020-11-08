package com.asenadev.sana.model.employee;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("messages")
	private List<String> messages;

	@SerializedName("errors")
	private List<String> errors;

	public int getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public List<String> getMessages(){
		return messages;
	}

	public List<String> getErrors(){
		return errors;
	}
}