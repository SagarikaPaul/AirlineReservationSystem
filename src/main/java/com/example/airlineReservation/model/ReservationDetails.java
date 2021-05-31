package com.example.airlineReservation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "reservationDetails")
@NamedQueries({ @NamedQuery(name = ReservationDetails.TICKET_DETAILS, query = ReservationDetails.TICKET_DETAILS_QUERY),
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BY_AGE, query = ReservationDetails.TICKET_DETAILS_BY_AGE_QUERY),
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BETWEEN_DATES, query = ReservationDetails.TICKET_DETAILS_BETWEEN_DATE_QUERY),
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BY_SEARCH, query = ReservationDetails.TICKET_DETAILS_BY_SEARCH_QUERY),
		@NamedQuery(name = ReservationDetails.USER_DETAILS_BY_CASHBACK, query = ReservationDetails.USER_DETAILS_BY_CASHBACK_QUERY)

})
public class ReservationDetails {

	// query for traveltype
	public static final String TICKET_DETAILS = "ReservationDetails.ticketDetails";
	public static final String TICKET_DETAILS_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address " + "where a.travelType = :travelType";

	// query for age
	public static final String TICKET_DETAILS_BY_AGE = "ReservationDetails.byAgeDetails";
	public static final String TICKET_DETAILS_BY_AGE_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join passengerDetails pd on rd.passengerDetails = pd.passengerId "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address " + "where pd.passengerAge < :passengerAge";

	// Query for in between dates

	public static final String TICKET_DETAILS_BETWEEN_DATES = "ReservationDetails.betweenDates";
	public static final String TICKET_DETAILS_BETWEEN_DATE_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join passengerDetails pd on rd.passengerDetails = pd.passengerId "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address "
			+ "where trunc(rd.bookingDate) between :startDate and :endDate";

	// Search query
	public static final String TICKET_DETAILS_BY_SEARCH = "ReservationDetails.bySearch";
	public static final String TICKET_DETAILS_BY_SEARCH_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join passengerDetails pd on rd.passengerDetails = pd.passengerId "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address "
			+ "where (:pnr IS NULL OR rd.pnr = :pnr) AND (:passengerAge IS NULL OR pd.passengerAge >= :passengerAge) "
			+ "AND (:source IS NULL OR rd.source = :source) AND (:destination IS NULL OR rd.destination = :destination)"
			+ "AND (:travelType IS NULL OR a.travelType = :travelType)";

	// CashBack query
	public static final String USER_DETAILS_BY_CASHBACK = "ReservatioinDetails.byCashBack";
	public static final String USER_DETAILS_BY_CASHBACK_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join passengerDetails pd on rd.passengerDetails = pd.passengerId "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address "
			+ "where (pd.passengerAge <= :age AND  a.travelType = :typeOfTravel)";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pnr;

	@NotBlank(message = "Source City should not be blank")
	private String source;

	@NotBlank(message = "Destination City should not be blank")
	private String destination;

	@JsonManagedReference
	@OneToOne(mappedBy = "reservationDetails", cascade = CascadeType.ALL)
	private Address address;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="passengerId", referencedColumnName = "passengerId")
	private PassengerDetails passengerDetails;

	private LocalDate bookingDate;

	public String bookingStatus;

	public BigDecimal bookingAmount;

	public ReservationDetails() {
	}

	public ReservationDetails(Long pnr, String source, String destination, Address address,
			PassengerDetails passengerDetails, LocalDate bookingDate, String bookingStatus, BigDecimal bookingAmount) {
		super();
		this.pnr = pnr;
		this.source = source;
		this.destination = destination;
		this.address = address;
		this.passengerDetails = passengerDetails;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
		this.bookingAmount = bookingAmount;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
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

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public BigDecimal getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(BigDecimal bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public PassengerDetails getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(PassengerDetails passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

}
