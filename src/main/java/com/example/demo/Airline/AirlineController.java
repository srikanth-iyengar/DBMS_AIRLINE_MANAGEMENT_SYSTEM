package com.example.demo.Airline;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightService;
import com.example.demo.User.CustomerService;
import com.example.demo.User.User;

@Controller
public class AirlineController {

	@Autowired
	FlightService flightService;
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/")
	public String home(Model m, HttpServletRequest auth) {
		Principal p = auth.getUserPrincipal();
		if (p == null) {
			return "airline";
		}
		String username = p.getName();
		User u = customerService.currentUser(username);
		m.addAttribute("user", u);
		return "loggedinuser";
	}
	
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	@GetMapping(value = "/find")
	public String searchFlights(@RequestParam String source, @RequestParam String destination, Model m) {
		Iterable<Flight> flights = flightService.FindFlight(source, destination);
		List<Flight> ff = new ArrayList<>();
		for (Flight f : flights) {
			ff.add(f);
		}
		m.addAttribute("list", ff);
		return "search";
	}
}
