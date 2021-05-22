package com.example.airlineReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.airlineReservation.model.ReservationDetails;

@Repository
public interface AirlineReservationRepository extends JpaRepository<ReservationDetails, Long>{

	
}
