package com.example.airlineReservation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.airlineReservation.model.ReservationDetails;

public class AirlineReservationRepositoryImpl implements TravelRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ReservationDetails> ticketDetails(String travelType) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.ticketDetails", ReservationDetails.class);
		query.setParameter("travelType", travelType);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

}
