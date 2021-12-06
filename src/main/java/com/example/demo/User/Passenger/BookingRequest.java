package com.example.demo.User.Passenger;

import java.util.List;

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
}
