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
}
