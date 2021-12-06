package com.example.demo.Airline;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.Flight.Flight;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Airline {
	@Id
	@Column(name = "registration_no", length = 20)
	private String airline_registration_no;
	@Column(name = "airline_name", length = 20, nullable = false)
	private String airline_name;
	
	@OneToMany(mappedBy = "airline")
	private Set<Flight> flights;
	
	
	public Airline(String id) {
		this.airline_registration_no = id;
	}
}
