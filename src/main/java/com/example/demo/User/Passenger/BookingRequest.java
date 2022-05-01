package com.example.demo.User.Passenger;

import java.util.List;

import com.example.demo.Booking.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingRequest {
	private Long flightid;
	private Long userid;
	private List<Passenger> passengers;
	private PaymentMode mode;
	private Double price;
	public Long getFlightid() {
		return flightid;
	}
	public void setFlightid(Long flightid) {
		this.flightid = flightid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public PaymentMode getMode() {
		return mode;
	}
	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public BookingRequest(Long flightid, Long userid, List<Passenger> passengers, PaymentMode mode, Double price) {
		super();
		this.flightid = flightid;
		this.userid = userid;
		this.passengers = passengers;
		this.mode = mode;
		this.price = price;
	}
	
	
}
