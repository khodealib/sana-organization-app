package com.asenadev.sana.model.referral;

import com.google.gson.annotations.SerializedName;

public class ReferralDataItem {

	@SerializedName("status_label")
	private String statusLabel;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("time")
	private String time;

	@SerializedName("employee")
	private Object employee;

	@SerializedName("status")
	private int status;

	@SerializedName("customer")
	private Customer customer;

	public String getStatusLabel(){
		return statusLabel;
	}

	public String getDescription(){
		return description;
	}

	public String getId(){
		return id;
	}

	public String getTime(){
		return time;
	}

	public Object getEmployee(){
		return employee;
	}

	public int getStatus(){
		return status;
	}

	public Customer getCustomer(){
		return customer;
	}
}