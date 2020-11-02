package com.asenadev.sana.model.login;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("errors")
	private List<Object> errors;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessages(List<Object> messages){
		this.messages = messages;
	}

	public List<Object> getMessages(){
		return messages;
	}

	public void setErrors(List<Object> errors){
		this.errors = errors;
	}

	public List<Object> getErrors(){
		return errors;
	}

	@Override
	public String toString() {
		return "LoginResponse{" +
				"code=" + code +
				", data=" + data +
				", messages=" + messages +
				", errors=" + errors +
				'}';
	}
}