package com.nathan.safetynetalerts.model;

import javax.validation.constraints.NotNull;

public class Firestation {
	
	@NotNull
	private String address;
	private String station;
	
	public Firestation() {
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
	
	
}
