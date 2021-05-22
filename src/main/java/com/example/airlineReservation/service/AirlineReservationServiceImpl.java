package com.example.airlineReservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.Status;
import com.example.airlineReservation.model.TravelDetails;
import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.repository.TravelRepository;

@Service
public class AirlineReservationServiceImpl implements AirlineReservationService {
	
	@Autowired
	private AirlineReservationRepository airlineReservationRepository;
	
	@Autowired
	private TravelRepository travelRepository;

	@Override
	public AirlineReservationOutput addUser(ReservationDetails airlineReservation) {
		AirlineReservationOutput airlineReservationOutput = new AirlineReservationOutput();
		Status status = new Status();
		List<Status> statuses = new ArrayList<Status>();
		ReservationDetails reservationDetailsAdded =  airlineReservationRepository.save(airlineReservation);
		if(!reservationDetailsAdded.getPnr().equals(null)) {
			status.setStatusLevel("Success");
			status.setMessage("Ticket booked successfully!!");
			statuses.add(status);
			airlineReservationOutput.setStatus(statuses);
			airlineReservationOutput.setReservationDetails(reservationDetailsAdded);
			return airlineReservationOutput;
		}
		status.setStatusLevel("Failure");
		status.setMessage("Booking Failed!!");
		statuses.add(status);
		airlineReservationOutput.setStatus(statuses);
		return airlineReservationOutput;
	}

	@Override
	public List<TravelDetails> getTravelDetailsByTravelType(String travelType) {
		return travelRepository.ticketDetails(travelType);
	}

}
