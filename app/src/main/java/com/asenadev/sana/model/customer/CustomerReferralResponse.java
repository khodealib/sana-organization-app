package com.asenadev.sana.model.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerReferralResponse {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("messages")
	private List<String> messages;

	@SerializedName("errors")
	private List<Object> errors;

	public int getCode(){
		return code;
	}

	public List<Object> getData(){
		return data;
	}

	public List<String> getMessages(){
		return messages;
	}

	public List<Object> getErrors(){
		return errors;
	}
}