package com.asenadev.sana.model.customer.arrival.departuelist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("arrivals")
	private List<ArrivalsItem> arrivals;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public List<ArrivalsItem> getArrivals(){
		return arrivals;
	}

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}
}