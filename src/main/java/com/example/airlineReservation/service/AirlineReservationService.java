package com.example.airlineReservation.service;

import java.util.List;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.TravelDetails;

public interface AirlineReservationService {

	public AirlineReservationOutput addUser(ReservationDetails airlineReservation);
	public List<TravelDetails> getTravelDetailsByTravelType(String travelType);

}
