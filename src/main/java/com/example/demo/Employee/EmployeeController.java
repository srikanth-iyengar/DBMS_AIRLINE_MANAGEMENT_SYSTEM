package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;
	
	@PostMapping
	@ResponseBody
	public String registerAdmin(@RequestBody RegistrationAdmin admin) {
		return employeeService.registerAdmin(admin);
	}
	
	@GetMapping("/home")
	@ResponseBody
	public String adminPage() {
		return "<h1>AdminPage</h1>";
	}
}
