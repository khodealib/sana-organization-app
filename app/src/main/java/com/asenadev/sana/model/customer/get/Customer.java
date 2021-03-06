package com.asenadev.sana.model.customer.get;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer{

	@SerializedName("national_code")
	private String nationalCode;

	@SerializedName("father_name")
	private String fatherName;

	@SerializedName("referrals")
	private List<ReferralItem> referrals;

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

	public String getNationalCode(){
		return nationalCode;
	}

	public String getFatherName(){
		return fatherName;
	}

	public List<ReferralItem> getReferrals(){
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