package com.example.airlineReservation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "reservationDetails")
@NamedQueries({
	@NamedQuery(name = ReservationDetails.TICKET_DETAILS, query = ReservationDetails.TICKET_DETAILS_QUERY)
})
public class ReservationDetails {
	
	public static final String TICKET_DETAILS = "ReservationDetails.ticketDetails";
	public static final String TICKET_DETAILS_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address "
			+ "where a.travelType = :travelType";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pnr;
	
	@NotBlank(message = "Name should not be blank")
	private String passengerName;
	
	@NotNull(message= "Age may not be empty")
	private Integer passengerAge;
	
	@NotNull(message= "Contact Number may not be empty")
	private Long passengerContactNumber;
	
	@NotBlank(message = "Email Id should not be blank")
	@Email(message = "Email Id should be in correct format")
	private String emailId;
	
	@NotBlank(message = "Source City should not be blank")
	private String source;
	
	@NotBlank(message = "Destination City should not be blank")
	private String destination;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "reservationDetails", cascade = CascadeType.ALL)
	private Address address;

	public ReservationDetails() {
	}

	public ReservationDetails(Long pnr, String passengerName, Integer passengerAge, Long passengerContactNumber,
			String emailId, String source, String destination, Address address) {
		this.pnr = pnr;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerContactNumber = passengerContactNumber;
		this.emailId = emailId;
		this.source = source;
		this.destination = destination;
		this.address = address;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
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

	public void setPassengerAge(Integer passengerAge) {
		this.passengerAge = passengerAge;
	}

	public Long getPassengerContactNumber() {
		return passengerContactNumber;
	}

	public void setPassengerContactNumber(Long passengerContactNumber) {
		this.passengerContactNumber = passengerContactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
