package com.asenadev.sana.model.referral.history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer{

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
	private Object picture;

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

	public Object getPicture(){
		return picture;
	}
}