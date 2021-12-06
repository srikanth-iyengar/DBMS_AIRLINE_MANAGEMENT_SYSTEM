package com.example.demo.Airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightService;

@Controller
@RequestMapping("/airline")
public class AirlineController {

	@Autowired
	FlightService flightService;

	@GetMapping(path = "/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("airline");
		return modelAndView;
	}
	
	@PostMapping(value = "/find")
	public Iterable<Flight> searchFlights(@RequestBody String source, @RequestBody String destination) {
		return flightService.FindFlight(source, destination);
	}
	
	@GetMapping(path = "/book")
	@ResponseBody
	public ModelAndView book() {
		ModelAndView m = new ModelAndView();
		m.setViewName("book");
		return m;
	}
}
