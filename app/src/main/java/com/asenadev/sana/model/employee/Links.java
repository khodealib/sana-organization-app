package com.asenadev.sana.model.employee;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("next")
	private String next;

	@SerializedName("last")
	private String last;

	@SerializedName("prev")
	private String prev;

	@SerializedName("first")
	private String first;

	public String getNext(){
		return next;
	}

	public String getLast(){
		return last;
	}

	public String getPrev(){
		return prev;
	}

	public String getFirst(){
		return first;
	}
}