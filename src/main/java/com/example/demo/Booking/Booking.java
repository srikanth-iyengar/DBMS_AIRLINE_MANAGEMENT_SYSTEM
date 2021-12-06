package com.example.demo.Booking;

import java.time.LocalDateTime;

//import javax.persistence.Entity;
//import javax.persistence.Id;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.Flight.Flight;
import com.example.demo.User.User;
import com.example.demo.User.Passenger.Passenger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Booking {

	@Id
	@Column(name = "booking_id")
	private String BookingId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_mode", nullable = false, length = 10)
	private PaymentMode paymentMode;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "booked_date")
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	@OneToMany(mappedBy = "booking")
	private Set<Passenger> passengers;
	
	public Booking(String bookingId) {
		super();
		BookingId = bookingId;
		this.paymentMode = PaymentMode.CREDITCARD;
		this.price = 1200.00;
	}
}