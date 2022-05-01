package com.example.demo.Flight;

import java.time.LocalDateTime;


public class RegisterFlight {
	private final LocalDateTime arrival;
	private final LocalDateTime departure;
	private final Integer capacity;
	private final String source;
	private final String destination;
	public LocalDateTime getArrival() {
		return arrival;
	}
	public LocalDateTime getDeparture() {
		return departure;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public String getSource() {
		return source;
	}
	public String getDestination() {
		return destination;
	}
	public RegisterFlight(LocalDateTime arrival, LocalDateTime departure, Integer capacity, String source,
			String destination) {
		super();
		this.arrival = arrival;
		this.departure = departure;
		this.capacity = capacity;
		this.source = source;
		this.destination = destination;
	}
	
	
}
