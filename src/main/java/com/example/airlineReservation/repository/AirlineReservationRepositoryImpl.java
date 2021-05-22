package com.example.airlineReservation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.airlineReservation.model.TravelDetails;

public class AirlineReservationRepositoryImpl implements TravelRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TravelDetails> ticketDetails(String travelType) {
		TypedQuery<TravelDetails> query = entityManager.createNamedQuery("ReservationDetails.ticketDetails", TravelDetails.class);
		query.setParameter("travelType", travelType);
		List<TravelDetails> result = query.getResultList();
		return result;
	}

}
