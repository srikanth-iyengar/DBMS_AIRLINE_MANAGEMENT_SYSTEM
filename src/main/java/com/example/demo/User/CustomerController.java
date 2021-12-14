package com.example.demo.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Booking.Booking;
import com.example.demo.Booking.BookingService;
import com.example.demo.Flight.FlightService;
import com.example.demo.User.Passenger.BookingRequest;
import com.example.demo.User.Passenger.Passenger;

@Controller
@RequestMapping(path = "/user")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	FlightService flightService;
	
	@Autowired
	BookingService bookingService;

	@PostMapping(path = "/register")
	public @ResponseBody String Register(@RequestBody RegistrationRequest request) {
		return customerService.register(request);
	}
	
	@GetMapping(path = "/register")
	public String Register() {
		return "RegisterUser";
	}
	@GetMapping(path = "/accessDenied")
	public String accessDenied() {
		return "accessdenied";
	}
	
	@GetMapping(path = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(HttpServletRequest auth) {
		Principal principal = auth.getUserPrincipal();
		if(principal == null) {
			return "<h1>Please login<h1>";
		}
		return "<h1>"+principal.getName()+"<h1>";
	}

	
	@GetMapping(value = "/book/{id}")
	public String bookFlight(Model M,@PathVariable String id) {
		return "FlightBooking";
	}
	
	@PostMapping(value="book/{id}")
	@ResponseBody
	public String confirmBooking(@PathVariable String id,@RequestBody List<Passenger> a, HttpServletRequest auth, Model m) {
		StringBuilder flight_id = new StringBuilder(id);
		a.remove(0);
		Long uid = Long.parseLong(flight_id.substring(8));
		Principal UserName = auth.getUserPrincipal();
		String userEmail = UserName.getName();
		Long customer_id = customerService.userId(userEmail);
		BookingRequest request = new BookingRequest(uid, customer_id, a);
		StringTokenizer st = new StringTokenizer(bookingService.bookTicket(request));
		return bookingService.bookTicket(request);
	}
	
	
	@GetMapping(value = "/dashboard")
	public String dashboard(Model m, HttpServletRequest auth) {
		Principal UserName = auth.getUserPrincipal();
		String userEmail = UserName.getName();
		
		User cur = customerService.currentUser(userEmail);
		List<Booking>bookings = bookingService.bookingRepo.findAll();
		List<Booking> thisuser = new ArrayList<>();
		for(Booking b : bookings) {
			if(b.getUser().getUsername().equals(cur.getEmail()))
			{
				thisuser.add(b);
			}
		}
		m.addAttribute("user", cur);
		m.addAttribute("list", thisuser);
		return "dashboarduser";
	}
	
	
	@GetMapping(value = "/success/{token}")
	public String sucess(@PathVariable String token, Model m) {
		m.addAttribute("token", token);
		return "success";
	}
}
