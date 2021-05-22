package com.example.airlineReservation.repository;

import java.util.List;

import com.example.airlineReservation.model.TravelDetails;

public interface TravelRepository {

	public List<TravelDetails> ticketDetails(String travelType);
}
