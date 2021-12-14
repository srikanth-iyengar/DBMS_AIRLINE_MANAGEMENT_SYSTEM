package com.example.demo.Employee;

import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Enquiry.Query;
import com.example.demo.Enquiry.QueryRepository;

@Controller
@RequestMapping("/admin")
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;
	
	@Autowired
	public QueryRepository queryRepository;
	@PostMapping
	@ResponseBody
	public String registerAdmin(@RequestBody RegistrationAdmin admin) {
		return employeeService.registerAdmin(admin);
	}
	
	@GetMapping("/home")
	public String adminPage(Model m, HttpServletRequest auth) {
		List<Query>allq = queryRepository.findAll();
		List<Query>thisadmin = new ArrayList<Query>();
		
		Principal p = auth.getUserPrincipal();
		Employee e = employeeService.employeeRepository.findByUserName(p.getName());
		for(Query q : allq) {
			if(q.getIsOpen()) {
				if(q.getEmployee().getUsername().equals(e.getUsername())) {
					thisadmin.add(q);
				}
			}
		}
		m.addAttribute("list", thisadmin);
		return "adminpage";
	}
	
	@GetMapping("/login")
	public String adminlogin() {
		return "adminlogin";
	}
	
	
	@GetMapping("/home/{id}")
	public String admin(@PathVariable String id) {
		StringBuilder qq = new StringBuilder(id);
		Long queryuid = Long.parseLong(id.substring(7));
		List<Query>aa = queryRepository.findAll();
		System.out.println(queryuid);
		Query thisquery = null;
		for(Query pp : aa) {
			if(pp.getQueryId() == queryuid) {
				thisquery = pp;
				break;
			}
		}
		thisquery.setIsOpen(false);
		queryRepository.save(thisquery);
		return "adminpage";
	}
}
