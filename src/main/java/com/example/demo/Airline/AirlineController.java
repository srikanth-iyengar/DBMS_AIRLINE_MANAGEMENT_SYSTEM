package com.example.demo.Airline;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Employee.Employee;
import com.example.demo.Employee.EmployeeRepository;
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

	@Autowired
	EmployeeRepository employeeRepository;

	static List<Flight> flights = new ArrayList<>();

	@GetMapping("/")
	public String home(Model m, HttpServletRequest auth) {
		Principal p = auth.getUserPrincipal();
		if (p == null) {
			return "airline";
		}
		String username = p.getName();
		User u = customerService.currentUser(username);
		if (u == null) {
			Employee e = employeeRepository.findByUserName(username);
			m.addAttribute("user", e.getUsername());
			return "loggedinuser";
		}
		m.addAttribute("user", u.getUsername());
		return "loggedinuser";
	}

	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	@GetMapping(value = "/find")
	public String searchFlights(Model m, @RequestParam String source, @RequestParam String destination) {
		List<Flight> flights = flightService.FindFlight(source, destination);
		List<Flight> to_be_displayed = new ArrayList<Flight>();
		for (Flight fli : flights) {
			if (fli.getSource().equals(source) && fli.getDestination().equals(destination)
					&& fli.getDeparture().isAfter(LocalDateTime.now())) {
				to_be_displayed.add(fli);
			}
		}
		System.out.println(to_be_displayed);
		m.addAttribute("list", to_be_displayed);
		return "search";
	}
	
	
}
