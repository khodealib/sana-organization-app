package com.asenadev.sana.model.customer.add;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerAddResponse {

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

	@Override
	public String toString() {
		return "CustomerData{" +
				"code=" + code +
				", data=" + data +
				", messages=" + messages +
				", errors=" + errors +
				'}';
	}
}