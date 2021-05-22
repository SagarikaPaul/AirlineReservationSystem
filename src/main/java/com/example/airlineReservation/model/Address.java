package com.example.airlineReservation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long addressId;
	
	// Domestic or Inter-national
	private String travelType;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "pnr", referencedColumnName = "pnr")
	private ReservationDetails reservationDetails;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<AddressDetail> addressDetail;

	public Address() {

	}

	public Address(Long addressId, String travelType, ReservationDetails reservationDetails,List<AddressDetail> addressDetail) {
		this.addressId = addressId;
		this.travelType = travelType;
		this.reservationDetails = reservationDetails;
		this.addressDetail = addressDetail;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public List<AddressDetail> getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(List<AddressDetail> addressDetail) {
		this.addressDetail = addressDetail;
	}

	public ReservationDetails getReservationDetails() {
		return reservationDetails;
	}

	public void setReservationDetails(ReservationDetails reservationDetails) {
		this.reservationDetails = reservationDetails;
	}	
	
}
