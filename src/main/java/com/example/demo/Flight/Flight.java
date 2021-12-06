package com.example.demo.Flight;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.Airline.Airline;
import com.example.demo.Booking.Booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flight_id")
	private Long flightId;
	
	@Column(name = "arrival_time",nullable = false)
	private LocalDateTime arrival;
	
	@Column(name = "departure_time", nullable = false)
	private LocalDateTime departure;
	
	@Column(name = "capacity", nullable = false)
	private Integer capacity;
	
	@Column(name = "source", nullable = false, length = 3)
	private String source;
	
	@Column(name = "destination", nullable = false, length = 3)
	private String destination;
	
	@ManyToOne
	@JoinColumn()
	private Airline airline;
	
	@OneToMany(mappedBy="flight")
	private Set<Booking> bookings;
	
	public Flight(
			LocalDateTime arrival, 
			LocalDateTime departure, 
			Integer capacity, 
			String source, 
			String destination) {
		super();
		this.arrival = arrival;
		this.departure = departure;
		this.capacity = capacity;
		this.source = source;
		this.destination = destination;
	}
	
	public Flight(Long id) {
		this.flightId = id;
	}
	
}
