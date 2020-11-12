package com.asenadev.sana.model.customer.add;

import com.google.gson.annotations.SerializedName;

public class Referral {

	@SerializedName("status_label")
	private String statusLabel;

	@SerializedName("employee_id")
	private String employeeId;

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("id")
	private String id;

	@SerializedName("employee_username")
	private String employeeUsername;

	@SerializedName("referred")
	private String referred;

	@SerializedName("status")
	private int status;

	public String getStatusLabel(){
		return statusLabel;
	}

	public String getEmployeeId(){
		return employeeId;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public String getId(){
		return id;
	}

	public String getEmployeeUsername(){
		return employeeUsername;
	}

	public String getReferred(){
		return referred;
	}

	public int getStatus(){
		return status;
	}
}