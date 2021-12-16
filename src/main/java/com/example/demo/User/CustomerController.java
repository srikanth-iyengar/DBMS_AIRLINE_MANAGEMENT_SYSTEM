package com.example.demo.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.Booking.Booking;
import com.example.demo.Booking.BookingRepository;
import com.example.demo.Booking.BookingService;
import com.example.demo.Booking.PaymentMode;
import com.example.demo.Employee.Employee;
import com.example.demo.Employee.EmployeeRepository;
import com.example.demo.Enquiry.Query;
import com.example.demo.Enquiry.QueryRepository;
import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightRepository;
import com.example.demo.Flight.FlightService;
import com.example.demo.User.Passenger.BookingRequest;
import com.example.demo.User.Passenger.Passenger;
import com.example.demo.User.Passenger.PassengerRepository;

@Controller
@RequestMapping(path = "/user")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	FlightService flightService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	QueryRepository queryRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	FlightRepository flightRepository;

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
	public String confirmBooking(@PathVariable String id,@RequestBody BookingForm bo, HttpServletRequest auth, Model m) {
		StringBuilder flight_id = new StringBuilder(id);
		System.out.println(bo.passengers + " " + bo.paymentmode + " " + bo.price);
		bo.passengers.remove(0);
		Long uid = Long.parseLong(flight_id.substring(8));
		Principal UserName = auth.getUserPrincipal();
		String userEmail = UserName.getName();
		Long customer_id = customerService.userId(userEmail);
		PaymentMode mode;
		if(bo.paymentmode.equals("DEBITCARD")) {
			mode = PaymentMode.DEBITCARD;
		}
		else if(bo.paymentmode.equals("CREDITCARD")) {
			mode = PaymentMode.CREDITCARD;
		}
		else if(bo.paymentmode.equals("NETBANKING")){
			mode = PaymentMode.NETBANKING;
		}
		else {
			mode = PaymentMode.UPI;
		}
		BookingRequest request = new BookingRequest(uid, customer_id, bo.passengers, mode, bo.price);
		return bookingService.bookTicket(request);
	}
	
	@PostMapping(value = "/query")
	public RedirectView askQuery(@RequestBody String query,HttpServletRequest auth) {
		RedirectView rv = new RedirectView();
		rv.setUrl("http://localhost:8080/user/dashboard");
		List<Employee>emp = employeeRepository.findAll();
		Comparator<Employee>com = (e1, e2) -> e1.getQueries().size() > e2.getQueries().size() ? 1 : -1;
		Collections.sort(emp, com);
		StringBuilder sb = new StringBuilder(query);
		String actual_query = sb.substring(10, sb.length() - 2);
		Employee emp_with_minimum_query = null;
		int min_query = Integer.MAX_VALUE;
		for(Employee e : emp) {
			min_query = Math.min(min_query, e.getQueries().size());
		}
		for(Employee e : emp) {
			if(e.getQueries().size() == min_query) {
				emp_with_minimum_query = e;
				break;
			}
		}
		Long id = emp_with_minimum_query.getId();
		Principal p = auth.getUserPrincipal();
		String username = p.getName();
		User u = customerService.currentUser(username);
		Long uid = u.getId();
		Query new_query = new Query();
		new_query.setDescription(actual_query);
		new_query.setEmployee(new Employee(id));
		new_query.setUser(new User(uid));
		new_query.setIsOpen(true);
		queryRepository.save(new_query);
		return rv;
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
	
	@GetMapping(value = "cancel/{id}")
	public String searchFlights(@PathVariable String id, Model m) {
		System.out.println(id);
		List<Passenger>pass = passengerRepository.findAll();
		Booking book = bookingRepository.findById(id).get();
		Flight flight = flightRepository.findById(book.getFlight().getFlightId()).get();
		for(Passenger p : pass) {
			if(p.getBooking().getBookingId().equals(id)) {
				passengerRepository.delete(p);
				flight.setCapacity(flight.getCapacity() + 1);
			}
		}
		flightRepository.save(flight);
		bookingRepository.delete(book);
		return "cancel";
	}
	
	@GetMapping(value = "dashboard/show/{id}")
	public String getPass(@PathVariable String id, Model m) {
		List<Passenger> pass = passengerRepository.findAll();
		List<Passenger> to_be_returned = new ArrayList<>();
		for(Passenger p : pass)
		{
			if(p.getBooking().getBookingId().equals(id)) {
				to_be_returned.add(p);
			}
		}
		m.addAttribute("list", to_be_returned);
		System.out.println(to_be_returned);
		return "pass";
	}
}
