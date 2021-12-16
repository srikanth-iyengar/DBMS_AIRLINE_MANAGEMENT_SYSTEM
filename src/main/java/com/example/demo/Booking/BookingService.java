package com.example.demo.Booking;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightRepository;
import com.example.demo.User.CustomerRepository;
import com.example.demo.User.User;
import com.example.demo.User.Passenger.BookingRequest;
import com.example.demo.User.Passenger.Passenger;
import com.example.demo.User.Passenger.PassengerRepository;

@Service
public class BookingService {

	@Autowired
	public BookingRepository bookingRepo;

	@Autowired
	public PassengerRepository passengerRepository;
	
	@Autowired
	public FlightRepository flightRepository;
	
	@Autowired
	public CustomerRepository customerRepository;

	public String bookTicket(BookingRequest request) {
		String generatedTransactionid = UUID.randomUUID().toString();
		Booking book = new Booking();
		
		System.out.println(request.getUserid() + " " + request.getFlightid());
		Flight f = flightRepository.findByFlightId(request.getFlightid());
		if(f.getCapacity() - request.getPassengers().size() < 0) {
			return "Could Not Book Ticket";
		}
		f.setCapacity(f.getCapacity() - request.getPassengers().size());
		flightRepository.save(f);
		book.setBookingId(generatedTransactionid);
		book.setFlight(new Flight(request.getFlightid()));
		book.setUser(new User(request.getUserid()));
		book.setPaymentMode(request.getMode());
		book.setPrice(request.getPrice());
		book.setDate(LocalDateTime.now());
		bookingRepo.save(book);
		System.out.println(request.getUserid());
		for (Passenger pass : request.getPassengers()) {
			Passenger temppass = new Passenger();
			temppass.setAadhar(pass.getAadhar());
			temppass.setAge(pass.getAge());
			temppass.setFirstName(pass.getFirstName());
			temppass.setLastName(pass.getLastName());
			temppass.setGender(pass.getGender());
			temppass.setUid(pass.getAadhar() + generatedTransactionid);
			temppass.setBooking(new Booking(generatedTransactionid));
			passengerRepository.save(temppass);
		}

		return generatedTransactionid;
	}
}
