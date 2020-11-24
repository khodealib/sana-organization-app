package com.asenadev.sana.model.referral.reference.complete;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompleteReferenceResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private CompleteReferenceData data;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("errors")
	private List<Object> errors;

	public int getCode(){
		return code;
	}

	public CompleteReferenceData getData(){
		return data;
	}

	public List<Object> getMessages(){
		return messages;
	}

	public List<Object> getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"CompleteReferenceResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",messages = '" + messages + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}