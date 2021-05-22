package com.example.airlineReservation.repository;

import java.util.List;

import com.example.airlineReservation.model.ReservationDetails;

public interface TravelRepository {

	public List<ReservationDetails> ticketDetails(String travelType);
}
