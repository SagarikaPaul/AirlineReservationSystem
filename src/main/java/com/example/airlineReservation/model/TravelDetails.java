package com.example.airlineReservation.model;

import java.math.BigDecimal;

public class TravelDetails {

	private Long pnr;
	private String passengerName;
	private Long passengerContactNumber;
	private String source;
	private String destination;
	private String travelType;
	private BigDecimal cashBack;

	public TravelDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelDetails(Long pnr, String passengerName, Long passengerContactNumber, String source, String destination,
			String travelType, BigDecimal cashBack) {
		super();
		this.pnr = pnr;
		this.passengerName = passengerName;
		this.passengerContactNumber = passengerContactNumber;
		this.source = source;
		this.destination = destination;
		this.travelType = travelType;
		this.cashBack = cashBack;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Long getPassengerContactNumber() {
		return passengerContactNumber;
	}

	public void setPassengerContactNumber(Long passengerContactNumber) {
		this.passengerContactNumber = passengerContactNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public BigDecimal getCashBack() {
		return cashBack;
	}

	public void setCashBack(BigDecimal cashBack) {
		this.cashBack = cashBack;
	}
	
	

}
