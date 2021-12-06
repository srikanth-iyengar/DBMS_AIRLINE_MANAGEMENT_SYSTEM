package com.example.demo.Flight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long>{
	Flight findBySource(String source);
	Flight findByDestination(String destination);
	List<Flight> findBySourceAndDestination(String source, String des);
	Flight findByFlightId(Long id);
}
