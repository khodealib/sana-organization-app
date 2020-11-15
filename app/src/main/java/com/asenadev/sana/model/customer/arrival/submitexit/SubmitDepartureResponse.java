package com.asenadev.sana.model.customer.arrival.submitexit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubmitDepartureResponse {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("errors")
	private List<Object> errors;

	public int getCode(){
		return code;
	}

	public List<Object> getData(){
		return data;
	}

	public List<Object> getMessages(){
		return messages;
	}

	public List<Object> getErrors(){
		return errors;
	}
}