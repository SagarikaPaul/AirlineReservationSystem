package com.example.airlineReservation.service;

import java.util.List;
import java.util.Optional;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.PassengerDetails;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.TravelDetails;

public interface AirlineReservationService {

	public PassengerDetails addUserDetails(PassengerDetails passengerDetails);	
	public AirlineReservationOutput bookTicket(ReservationDetails airlineReservation);
	public List<TravelDetails> getTravelDetailsByTravelType(String travelType);
	public List<TravelDetails> getTravelDetailsByAge(int passengerAge);
	public List<TravelDetails> getTravellersBetweenDates(String startDate, String endDate);
	public Optional<ReservationDetails> getPnrDetails(Long pnr);
	public ReservationDetails updateUserDetails(ReservationDetails reservationDetails);
	public List<TravelDetails> getAllTravellers(Long pnr, int passengerAge, String source, String destination, String travelType);
	public ReservationDetails getCancelBooking (Long pnr);
	public List<TravelDetails> getAllTravellersElligableForCashBack(int passengerAge, String travelType);

}
