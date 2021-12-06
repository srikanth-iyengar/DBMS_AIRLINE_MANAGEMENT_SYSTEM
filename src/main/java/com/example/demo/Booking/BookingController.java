package com.example.demo.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.User.Passenger.BookingRequest;

@Controller
@RequestMapping("/book")
public class BookingController {
	
	@Autowired
	public BookingService bookingService;
	
	@PostMapping
	@ResponseBody
	public String bookTicker(@RequestBody BookingRequest request) {
		System.out.println();
		return bookingService.bookTicket(request);
	}
}
