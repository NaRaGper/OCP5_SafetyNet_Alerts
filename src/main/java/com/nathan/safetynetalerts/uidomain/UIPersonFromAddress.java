package com.nathan.safetynetalerts.uidomain;

import java.util.ArrayList;
import java.util.List;

public class UIPersonFromAddress {
	
	private List<UIPerson> persons = new ArrayList<>();
	private int nombreAdultes;
	private int nombreEnfants;
	
	public List<UIPerson> getPersons() {
		return persons;
	}
	public void setPersons(List<UIPerson> persons) {
		this.persons = persons;
	}
	public int getNombreAdultes() {
		return nombreAdultes;
	}
	public void setNombreAdultes(int nombreAdultes) {
		this.nombreAdultes = nombreAdultes;
	}
	public int getNombreEnfants() {
		return nombreEnfants;
	}
	public void setNombreEnfants(int nombreEnfants) {
		this.nombreEnfants = nombreEnfants;
	}
	
	
}
