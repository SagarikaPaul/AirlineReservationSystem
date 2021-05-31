package com.example.airlineReservation.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.airlineReservation.model.ReservationDetails;

@Repository
public interface TravelRepository {
	
	public ReservationDetails addBooking(ReservationDetails reservationDetails);
	public ReservationDetails updateBooking(ReservationDetails reservationDetails);
	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType);
	public List<ReservationDetails> getTravelDetailsByAge(int passengerAge);
	public List<ReservationDetails> getTravellersBetweenDates(String startDate, String endDate);
	public List<ReservationDetails> getAllTravellersDetails(Long pnr, int passengerAge, String source, String destination, String travelType);
	public List<ReservationDetails> getAllTravellersForCashBack(int passengerAge, String travelType);
}
