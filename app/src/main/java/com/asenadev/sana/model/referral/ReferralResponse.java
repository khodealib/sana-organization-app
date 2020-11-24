package com.asenadev.sana.model.referral;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReferralResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<ReferralDataItem> data;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("errors")
	private List<Object> errors;

	public int getCode(){
		return code;
	}

	public List<ReferralDataItem> getData(){
		return data;
	}

	public List<Object> getMessages(){
		return messages;
	}

	public List<Object> getErrors(){
		return errors;
	}
}