package com.example.demo.Flight;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterFlight {
	private final LocalDateTime arrival;
	private final LocalDateTime departure;
	private final Integer capacity;
	private final String source;
	private final String destination;
}
