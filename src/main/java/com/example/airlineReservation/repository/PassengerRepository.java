package com.example.airlineReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.airlineReservation.model.PassengerDetails;

public interface PassengerRepository extends JpaRepository<PassengerDetails, Long> {

}
