package com.example.demo.User;

import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Flight.FlightService;

@Controller
@RequestMapping(path = "/user")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	FlightService flightService;

	@PostMapping(path = "/register")
	public @ResponseBody String Register(@RequestBody RegistrationRequest request) {
		return customerService.register(request);
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

	
	@GetMapping(value = "/book")
	@ResponseBody
	public String BookTicket() {
		return UUID.randomUUID().toString();
	}
	
}
