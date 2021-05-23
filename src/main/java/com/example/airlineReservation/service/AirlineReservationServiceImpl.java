package com.example.airlineReservation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		airlineReservation.setBookingDate(LocalDate.now());
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
		List<TravelDetails> travelDetails = new ArrayList<>();
		List<ReservationDetails> reservationDetails = travelRepository.getTravelDetailsByTravelType(travelType);
		travelDetails = reservationDetails.stream()
							.map(reservationDetail -> new TravelDetails(reservationDetail.getPnr(), 
									reservationDetail.getPassengerName(), reservationDetail.getPassengerContactNumber(),
									reservationDetail.getSource(), reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType()))
							.collect(Collectors.toList());
		return travelDetails;
	}

	@Override
	public List<TravelDetails> getTravelDetailsByAge(int passengerAge) {
		List<ReservationDetails> reservationDetails = travelRepository.getTravelDetailsByAge(passengerAge);
		List<TravelDetails> travelDetails  = new ArrayList<>();
		travelDetails = reservationDetails.stream().map(reservationDetail ->
											new TravelDetails(reservationDetail.getPnr(), reservationDetail.getPassengerName(),
													reservationDetail.getPassengerContactNumber(), reservationDetail.getSource(),
													reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType()))
				.collect(Collectors.toList());
		return travelDetails;
	}

	@Override
	public List<TravelDetails> getTravellersBetweenDates(String startDate, String endDate) {
		List<ReservationDetails> reservationDetails = travelRepository.getTravellersBetweenDates(startDate, endDate);
		List<TravelDetails> travelDetails  = new ArrayList<>();
		travelDetails = reservationDetails.stream().map(reservationDetail ->
		new TravelDetails(reservationDetail.getPnr(), reservationDetail.getPassengerName(),
				reservationDetail.getPassengerContactNumber(), reservationDetail.getSource(),
				reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType()))
				.collect(Collectors.toList());
		return travelDetails;
	}

	@Override
	public Optional<ReservationDetails> getPnrDetails(Long pnr) {
		return airlineReservationRepository.findById(pnr) ;
	}

	@Override
	public ReservationDetails updateUserDetails(ReservationDetails reservationDetails) {
		return airlineReservationRepository.save(reservationDetails);
	}


	@Override
	public List<TravelDetails> getAllTravellers(Long pnr, int passengerAge, String source, String destination,
			String travelType) {
		List<TravelDetails> travelDetails = new ArrayList<>();
		List<ReservationDetails> reservationDetails = travelRepository.getAllTravellersDetails(pnr, passengerAge,source,destination,travelType);
		travelDetails =  reservationDetails.stream().map(reservationDetail -> new TravelDetails(reservationDetail.getPnr(), reservationDetail.getPassengerName(), 
				reservationDetail.getPassengerContactNumber(), reservationDetail.getSource(), reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType()))
				.collect(Collectors.toList());

		return travelDetails;
	}

}
