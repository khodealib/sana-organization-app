package com.asenadev.sana.model.customer.arrival.departuelist;

import com.google.gson.annotations.SerializedName;

public class ArrivalsItem{

	@SerializedName("arrival_time")
	private String arrivalTime;

	@SerializedName("id")
	private String id;

	@SerializedName("departure_time")
	private String departureTime;

	@SerializedName("customer")
	private CustomerArrivalDeparture customerArrivalDeparture;

	public String getArrivalTime(){
		return arrivalTime;
	}

	public String getId(){
		return id;
	}

	public String getDepartureTime(){
		return departureTime;
	}

	public CustomerArrivalDeparture getCustomerArrivalDeparture(){
		return customerArrivalDeparture;
	}

	@Override
	public String toString() {
		return "ArrivalsItem{" +
				"arrivalTime='" + arrivalTime + '\'' +
				", id='" + id + '\'' +
				", departureTime='" + departureTime + '\'' +
				", customerArrivalDeparture=" + customerArrivalDeparture +
				'}';
	}
}