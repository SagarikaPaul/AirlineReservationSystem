package com.example.airlineReservation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "passengerDetails")
public class PassengerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;
	
	@NotBlank(message = "Name should not be blank")
	private String passengerName;
	
	@NotNull(message = "Age may not be empty")
	private int passengerAge;
	
	@NotNull(message = "Contact Number may not be empty")
	private Long contactNumber;
	
	@NotBlank(message = "Email Id should not be blank")
	@Email(message = "Email Id should be in correct format")
	private String email;
	
	@NotBlank(message = "Gender should not be blank")
	private String gender;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "passengerDetails", cascade = CascadeType.ALL)
	private List<ReservationDetails> reservationDetails;

	public PassengerDetails() {

	}

	public PassengerDetails(Long passengerId, String passengerName, int passengerAge, Long contactNumber,String email,String gender, List<ReservationDetails> reservationDetails) {
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.contactNumber = contactNumber;
		this.email = email;
		this.gender = gender;
		this.reservationDetails = reservationDetails;

	}


	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<ReservationDetails> getReservationDetails() {
		return reservationDetails;
	}

	public void setReservationDetails(List<ReservationDetails> reservationDetails) {
		this.reservationDetails = reservationDetails;
	}


}
