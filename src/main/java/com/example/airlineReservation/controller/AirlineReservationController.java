 package com.example.airlineReservation.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.Status;
import com.example.airlineReservation.model.TravelDetails;
import com.example.airlineReservation.service.AirlineReservationService;
import com.example.airlineReservation.util.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/airlineReservation")
public class AirlineReservationController {
	
	@Autowired
	private AirlineReservationService airlineReservationService;
	
	//Add user to book flight
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "book new ticket for the user", response = AirlineReservationOutput.class, responseContainer = "String")
	public ResponseEntity<String> addUser(@Valid @RequestBody ReservationDetails reservationDetails) throws JsonProcessingException{
		ObjectMapper customerMapper = new ObjectMapper();
		AirlineReservationOutput airlineReservationOutput = airlineReservationService.addUser(reservationDetails);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
		.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
		.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
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
	
	//Get Booking Details of all travellers by Travel Type
	@GetMapping("/travelType/{travelType}")
	@ApiOperation(value = "Get booking details of all travellers by Travel Type", response = TravelDetails.class, responseContainer = "List")
	public ResponseEntity<List<TravelDetails>> getTravelDetailsByTravelType(@PathVariable String travelType){
		List<TravelDetails> travelDetails = airlineReservationService.getTravelDetailsByTravelType(travelType);
		return new ResponseEntity<List<TravelDetails>>(travelDetails, HttpStatus.OK);	
	}
	
	//Get Booking Details of all travellers below a particular age
	@GetMapping("/age/{passengerAge}")
	@ApiOperation(value = "Get booking details of all travellers below a particular age", response = TravelDetails.class, responseContainer = "List")
	public ResponseEntity<List<TravelDetails>> getTravelDetailsByAge(@PathVariable int passengerAge ){
		List<TravelDetails> travelDetails =airlineReservationService.getTravelDetailsByAge(passengerAge);
		return new ResponseEntity<List<TravelDetails>>(travelDetails,HttpStatus.OK);	
	}
	
	// Get all the bookings done between two specific dates
	@GetMapping("/dates/{startDate}/{endDate}")
	@ApiOperation(value = " Gets all the bookings between two dates", response = TravelDetails.class, responseContainer = "List")
	public ResponseEntity<List<TravelDetails>> getsAllTravellersBetweenDates(@PathVariable String startDate, @PathVariable String endDate){
		List<TravelDetails> travellersList = airlineReservationService.getTravellersBetweenDates(startDate,endDate);
		return new ResponseEntity<List<TravelDetails>>(travellersList, HttpStatus.OK);
	}
	
	//Update any details of the user by id
	@PutMapping(path = "/{pnr}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update details of the user by id", response = ReservationDetails.class, responseContainer = "ReservationDetails" )
	public ResponseEntity<String> updateUserDetails(@PathVariable Long pnr, @Valid @RequestBody ReservationDetails reservationDetails ) throws JsonProcessingException{
		ObjectMapper customerMapper = new ObjectMapper();
		String[] ignoreProperties = {"pnr", "address", "bookingDate"};
		if (airlineReservationService.getPnrDetails(pnr).isPresent()){
			ReservationDetails existingDetails = airlineReservationService.getPnrDetails(pnr).get();
			BeanUtils.copyProperties(reservationDetails, existingDetails, ignoreProperties);
			ReservationDetails updatedReservationDetails = airlineReservationService.updateUserDetails(existingDetails);
			customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
			.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return new ResponseEntity<>(customerMapper.writeValueAsString(updatedReservationDetails), HttpStatus.OK);
		}
		throw new ValidationException("No User Found for the pnr ");
	}
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ValidationException.class)
	Message exceptionHandler(ValidationException e) {
		return new Message(e.getMessage());
	}
	
	//Search travel details by parameters
	@GetMapping("/booking/search")
	@ApiOperation(value = "Search travel details by parameters", response = TravelDetails.class, responseContainer = "TravelDetails")
	public ResponseEntity<List<TravelDetails>> getTravelDetails(@RequestParam(required = false) Long pnr, 
																@RequestParam(required = false) int passengerAge, 
																@RequestParam(required = false) String source, 
																@RequestParam(required = false) String destination,
																@RequestParam(required = false) String travelType){
		List<TravelDetails> travellersList = airlineReservationService.getAllTravellers(pnr, passengerAge, source, destination, travelType);
		return new ResponseEntity<List<TravelDetails>>(travellersList,HttpStatus.OK);
	}
	
}
