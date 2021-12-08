package com.example.demo.Airline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightService;

@Controller
public class AirlineController {

	@Autowired
	FlightService flightService;
	
	@GetMapping("/")
	public String home(Model m) {
		return "airline";
	}
	
	@GetMapping(value = "/find")
	public String searchFlights(@RequestParam String source, @RequestParam String destination, Model m) {
		Iterable<Flight>flights = flightService.FindFlight(source, destination);
		List<Flight>ff = new ArrayList<>();
		for(Flight f : flights) {
			if(f.getSource().equals(source) && f.getDestination().equals(destination)) {
				ff.add(f);
			}
		}
		m.addAttribute("list", ff);
		System.out.println(ff);
		return "flight";
	}

	@GetMapping(path = "/book")
	@ResponseBody
	public ModelAndView book() {
		ModelAndView m = new ModelAndView();
		m.setViewName("book");
		return m;
	}
}
