package com.example.demo.User;

import java.util.List;

import com.example.demo.Booking.PaymentMode;
import com.example.demo.User.Passenger.Passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingForm {
	List<Passenger>passengers;
	Double price;
	String paymentmode;
}
