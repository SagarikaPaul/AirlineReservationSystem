package com.example.airlineReservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "addressDetail")
public class AddressDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String addressType;
	private String addressName;
	private String addressLocation;
	private String pincode;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "adressId", referencedColumnName = "addressId")
	private Address address;
	
	public AddressDetail() {

	}

	public AddressDetail(Long id, String addressType, String addressName, String addressLocation, String pincode, Address address) {
		this.id = id;
		this.addressType = addressType;
		this.addressName = addressName;
		this.addressLocation = addressLocation;
		this.pincode = pincode;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressLocation() {
		return addressLocation;
	}

	public void setAddressLocation(String addressLocation) {
		this.addressLocation = addressLocation;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
