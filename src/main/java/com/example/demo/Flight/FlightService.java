package com.example.demo.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	public String registerFlight(RegisterFlight flight) {
		flightRepository.save(
		new Flight(
				flight.getArrival(),
				flight.getDeparture(),
				flight.getCapacity(),
				flight.getSource(),
				flight.getDestination()
				)
		);
		return "flight registered successfully";
	}
	
	public Iterable<Flight> FindFlight(String source, String Destination) {
		return flightRepository.findBySourceAndDestination(source, Destination)
				.stream().filter(
				t -> t.getDeparture().isAfter(LocalDateTime.now())).
				collect(Collectors.toList());
		
	}
}
