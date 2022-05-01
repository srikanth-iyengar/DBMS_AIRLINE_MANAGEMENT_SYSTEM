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

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
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
	
	public Booking() {
		
	}
	public Booking(String bookingId, PaymentMode paymentMode, Double price, LocalDateTime date, Flight flight,
			User user, Set<Passenger> passengers) {
		super();
		BookingId = bookingId;
		this.paymentMode = paymentMode;
		this.price = price;
		this.date = date;
		this.flight = flight;
		this.user = user;
		this.passengers = passengers;
	}

	@OneToMany(mappedBy = "booking")
	private Set<Passenger> passengers;
	
	public Booking(String bookingId) {
		super();
		BookingId = bookingId;
		this.paymentMode = PaymentMode.CREDITCARD;
		this.price = 1200.00;
	}

	public String getBookingId() {
		return BookingId;
	}

	public void setBookingId(String bookingId) {
		BookingId = bookingId;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	
}