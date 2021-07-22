package com.nathan.safetynetalerts.database;

import com.nathan.safetynetalerts.init.Datas;

public class Database {
	private static Database instance;
	private static Datas data;
	
	private Database() {
		
	}
	
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Datas getData() {
		return data;
	}

	public void setData(Datas data) {
		Database.data = data;
	}
	
}
