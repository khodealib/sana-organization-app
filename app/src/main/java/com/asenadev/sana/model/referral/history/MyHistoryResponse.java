package com.asenadev.sana.model.referral.history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyHistoryResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("errors")
	private List<Object> errors;

	public int getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public List<Object> getMessages(){
		return messages;
	}

	public List<Object> getErrors(){
		return errors;
	}
}