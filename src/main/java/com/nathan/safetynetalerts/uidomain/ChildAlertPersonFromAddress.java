package com.nathan.safetynetalerts.uidomain;

import java.util.ArrayList;
import java.util.List;

public class ChildAlertPersonFromAddress {
	
	private List<ChildAlertPerson> children = new ArrayList<>();
	private List<ChildAlertPerson> adults = new ArrayList<>();
	
	public ChildAlertPersonFromAddress() {
		super();
	}
	
	public ChildAlertPersonFromAddress(List<ChildAlertPerson> children) {
		super();
		this.children = children;
	}

	public List<ChildAlertPerson> getChildren() {
		return children;
	}

	public void setChildren(List<ChildAlertPerson> children) {
		this.children = children;
	}

	public List<ChildAlertPerson> getAdults() {
		return adults;
	}

	public void setAdults(List<ChildAlertPerson> adults) {
		this.adults = adults;
	}

	
}
