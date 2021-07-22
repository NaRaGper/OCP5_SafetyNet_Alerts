package com.nathan.safetynetalerts.model;

import javax.validation.constraints.NotNull;

public class Firestation {
	
	@NotNull
	private String address;
	private int station;
	
	public Firestation() {
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}
	
	
}
