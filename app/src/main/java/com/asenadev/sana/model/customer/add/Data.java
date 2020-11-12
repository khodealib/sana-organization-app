package com.asenadev.sana.model.customer.add;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("national_code")
	private String nationalCode;

	@SerializedName("father_name")
	private String fatherName;

	@SerializedName("referrals")
	private List<Referral> referrals;

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

	public List<Referral> getReferrals(){
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

	@Override
	public String toString() {
		return "Data{" +
				"nationalCode='" + nationalCode + '\'' +
				", fatherName='" + fatherName + '\'' +
				", referrals=" + referrals +
				", mobile='" + mobile + '\'' +
				", lastName='" + lastName + '\'' +
				", id='" + id + '\'' +
				", firstName='" + firstName + '\'' +
				", picture=" + picture +
				'}';
	}
}