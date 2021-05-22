package com.example.airlineReservation.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.Status;
import com.example.airlineReservation.model.TravelDetails;
import com.example.airlineReservation.service.AirlineReservationService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/v1/airlineReservation")
public class AirlineReservationController {
	
	@Autowired
	private AirlineReservationService airlineReservationService;
	
	//Add user to book flight
	@PostMapping
	public ResponseEntity<String> addUser(@Valid @RequestBody ReservationDetails reservationDetails) throws JsonProcessingException{
		ObjectMapper customerMapper = new ObjectMapper();
		AirlineReservationOutput airlineReservationOutput = airlineReservationService.addUser(reservationDetails);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		return new ResponseEntity<>(customerMapper.writeValueAsString(airlineReservationOutput), HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<Status> exceptionHandler(MethodArgumentNotValidException e){
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<Status> status = fieldErrors.stream().
				map(fieldError -> new Status("failure", fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		return status;
	}
	
	//Get Booking Details by Travel Type
	@GetMapping("/{travelType}")
	public ResponseEntity<List<TravelDetails>> getTravelDetailsByTravelType(@PathVariable String travelType){
		List<TravelDetails> travelDetails = airlineReservationService.getTravelDetailsByTravelType(travelType);
		return new ResponseEntity<List<TravelDetails>>(travelDetails, HttpStatus.OK);
		
	}

}
