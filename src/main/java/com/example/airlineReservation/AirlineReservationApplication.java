package com.example.airlineReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.airlineReservation.repository.AirlineReservationRepositoryImpl;
import com.example.airlineReservation.repository.TravelRepository;
import com.example.airlineReservation.service.AirlineReservationService;
import com.example.airlineReservation.service.AirlineReservationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AirlineReservationApplication {
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public AirlineReservationService airlineReservationService() {
		return new AirlineReservationServiceImpl();
	}
	
	@Bean
	public TravelRepository travelRepository() {
		return new AirlineReservationRepositoryImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationApplication.class, args);
	}

}
