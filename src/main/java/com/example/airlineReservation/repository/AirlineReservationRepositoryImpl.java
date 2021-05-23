package com.example.airlineReservation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.TravelDetails;

public class AirlineReservationRepositoryImpl implements TravelRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.ticketDetails", ReservationDetails.class);
		query.setParameter("travelType", travelType);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

	@Override
	public List<ReservationDetails> getTravelDetailsByAge(int passengerAge) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.byAgeDetails", ReservationDetails.class);
		query.setParameter("passengerAge", passengerAge);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

	@Override
	public List<ReservationDetails> getTravellersBetweenDates(String startDate, String endDate) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.betweenDates", ReservationDetails.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

	@Override
	public List<ReservationDetails> getAllTravellersDetails(Long pnr, int passengerAge, String source, String destination,String travelType) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.bySearch", ReservationDetails.class);
		query.setParameter("pnr", pnr);
		query.setParameter("passengerAge", passengerAge);
		query.setParameter("source", source);
		query.setParameter("destination", destination);
		query.setParameter("travelType", travelType);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

}
