package com.asenadev.sana.model.customer.referralcompleted;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("reference_id")
	private String referenceId;

	public String getReferenceId(){
		return referenceId;
	}
}