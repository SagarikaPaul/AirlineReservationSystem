package com.example.airlineReservation.model;

import java.util.List;

public class AirlineReservationOutput {

	private List<Status> status;
	private ReservationDetails reservationDetails;

	public AirlineReservationOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AirlineReservationOutput(List<Status> status, ReservationDetails reservationDetails) {
		super();
		this.status = status;
		this.reservationDetails = reservationDetails;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public ReservationDetails getReservationDetails() {
		return reservationDetails;
	}

	public void setReservationDetails(ReservationDetails reservationDetails) {
		this.reservationDetails = reservationDetails;
	}


}
