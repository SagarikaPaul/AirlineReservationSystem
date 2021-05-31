package com.example.airlineReservation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlineReservation.model.PassengerDetails;
import com.example.airlineReservation.service.AirlineReservationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/passenger")
public class PassengerController {
	
	@Autowired
	private AirlineReservationService airlineReservationService;
	
	@PostMapping("/addUser")
	@ApiOperation(value = "Add new User Details", response = PassengerDetails.class, responseContainer = "PassengerDetails")
	public ResponseEntity<PassengerDetails> addUserDetails(@Valid @RequestBody PassengerDetails passengerDetails){
		PassengerDetails details = airlineReservationService.addUserDetails(passengerDetails);
		return new ResponseEntity<PassengerDetails>(details,HttpStatus.OK);
	}


}
