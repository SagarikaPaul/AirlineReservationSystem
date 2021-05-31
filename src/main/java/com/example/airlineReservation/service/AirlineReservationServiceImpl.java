package com.example.airlineReservation.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.PassengerDetails;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.Status;
import com.example.airlineReservation.model.TravelDetails;
import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.repository.PassengerRepository;
import com.example.airlineReservation.repository.TravelRepository;

@Service
public class AirlineReservationServiceImpl implements AirlineReservationService {
	
	@Autowired
	private AirlineReservationRepository airlineReservationRepository;
	
	@Autowired
	private TravelRepository travelRepository;
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	
	@Override
	public PassengerDetails addUserDetails(PassengerDetails passengerDetails) {
		return passengerRepository.save(passengerDetails);
	}

	@Override
	public AirlineReservationOutput bookTicket(ReservationDetails airlineReservation) {
		AirlineReservationOutput airlineReservationOutput = new AirlineReservationOutput();
		Status status = new Status();
		List<Status> statuses = new ArrayList<Status>();
		airlineReservation.setBookingDate(LocalDate.now());
		airlineReservation.setBookingStatus("Booked");
		ReservationDetails reservationDetailsAdded =  travelRepository.addBooking(airlineReservation);
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
									reservationDetail.getPassengerDetails().getPassengerName(), reservationDetail.getPassengerDetails().getContactNumber(),
									reservationDetail.getSource(), reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(), null))
							.collect(Collectors.toList());
		return travelDetails;
	}

	@Override
	public List<TravelDetails> getTravelDetailsByAge(int passengerAge) {
		List<ReservationDetails> reservationDetails = travelRepository.getTravelDetailsByAge(passengerAge);
		List<TravelDetails> travelDetails  = new ArrayList<>();
		travelDetails = reservationDetails.stream().map(reservationDetail ->
											new TravelDetails(reservationDetail.getPnr(), reservationDetail.getPassengerDetails().getPassengerName(),
													reservationDetail.getPassengerDetails().getContactNumber(), reservationDetail.getSource(),
													reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(),null))
				.collect(Collectors.toList());
		return travelDetails;
	}

	@Override
	public List<TravelDetails> getTravellersBetweenDates(String startDate, String endDate) {
		List<ReservationDetails> reservationDetails = travelRepository.getTravellersBetweenDates(startDate, endDate);
		List<TravelDetails> travelDetails  = new ArrayList<>();
		travelDetails = reservationDetails.stream().map(reservationDetail ->
		new TravelDetails(reservationDetail.getPnr(), reservationDetail.getPassengerDetails().getPassengerName(),
				reservationDetail.getPassengerDetails().getContactNumber(), reservationDetail.getSource(),
				reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(), null))
				.collect(Collectors.toList());
		return travelDetails;
	}

	@Override
	public Optional<ReservationDetails> getPnrDetails(Long pnr) {
		return airlineReservationRepository.findById(pnr) ;
	}

	@Override
	public ReservationDetails updateUserDetails(ReservationDetails reservationDetails) {
		return travelRepository.updateBooking(reservationDetails);
	}


	@Override
	public List<TravelDetails> getAllTravellers(Long pnr, int passengerAge, String source, String destination,
			String travelType) {
		List<TravelDetails> travelDetails = new ArrayList<>();
		List<ReservationDetails> reservationDetails = travelRepository.getAllTravellersDetails(pnr, passengerAge,source,destination,travelType);
		travelDetails =  reservationDetails.stream().map(reservationDetail -> new TravelDetails(reservationDetail.getPnr(), reservationDetail.getPassengerDetails().getPassengerName(), 
				reservationDetail.getPassengerDetails().getContactNumber(), reservationDetail.getSource(), reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(), null))
				.collect(Collectors.toList());

		return travelDetails;
	}

	@Override
	public ReservationDetails getCancelBooking(Long pnr) {
		ReservationDetails existingDetails = airlineReservationRepository.findById(pnr).get();
		if(existingDetails != null) {
			if(existingDetails.getBookingStatus() == "Booked") {
				String updatedBookingStatus = "Booking cancelled";
				existingDetails.setBookingStatus(updatedBookingStatus);
				airlineReservationRepository.save(existingDetails);
			}
		}
		return existingDetails;
	}

	@Override
	public List<TravelDetails> getAllTravellersElligableForCashBack(int passengerAge, String travelType) {
		List<ReservationDetails> reservationDetails = travelRepository.getAllTravellersForCashBack(passengerAge, travelType);
		List<TravelDetails> travelDetails = reservationDetails.stream().map(reservationDetail -> new TravelDetails(reservationDetail.getPnr(),
				reservationDetail.getPassengerDetails().getPassengerName(), reservationDetail.getPassengerDetails().getContactNumber(), reservationDetail.getSource(),
				reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(), getCashbackAmount(reservationDetail.getBookingAmount()))).collect(Collectors.toList());
				
		return travelDetails;
	}
	
	public static BigDecimal getCashbackAmount(BigDecimal bookingAmount) {
		return bookingAmount.multiply(new BigDecimal((double)20/100));
	}

}
