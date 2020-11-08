package com.asenadev.sana.model.customer;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("customer")
	private Customer customer;

	public Customer getCustomer(){
		return customer;
	}
}