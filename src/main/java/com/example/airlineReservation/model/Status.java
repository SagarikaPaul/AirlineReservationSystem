package com.example.airlineReservation.model;

public class Status {

	private String statusLevel;
	private String message;

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(String statusLevel, String message) {
		super();
		this.statusLevel = statusLevel;
		this.message = message;
	}

	public String getStatusLevel() {
		return statusLevel;
	}

	public void setStatusLevel(String statusLevel) {
		this.statusLevel = statusLevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
