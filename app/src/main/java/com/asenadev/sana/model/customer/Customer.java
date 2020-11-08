package com.asenadev.sana.model.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("errors")
	private List<Object> errors;

	@SerializedName("national_code")
	private String nationalCode;

	@SerializedName("father_name")
	private String fatherName;

	@SerializedName("referrals")
	private List<Object> referrals;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("picture")
	private String picture;

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

	public String getNationalCode(){
		return nationalCode;
	}

	public String getFatherName(){
		return fatherName;
	}

	public List<Object> getReferrals(){
		return referrals;
	}

	public String getMobile(){
		return mobile;
	}

	public String getLastName(){
		return lastName;
	}

	public String getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getPicture(){
		return picture;
	}
}