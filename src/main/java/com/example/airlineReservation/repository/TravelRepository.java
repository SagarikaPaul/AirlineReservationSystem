package com.example.airlineReservation.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.airlineReservation.model.ReservationDetails;

@Repository
public interface TravelRepository {

	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType);
	public List<ReservationDetails> getTravelDetailsByAge(int passengerAge);
	public List<ReservationDetails> getTravellersBetweenDates(String startDate, String endDate);
}
