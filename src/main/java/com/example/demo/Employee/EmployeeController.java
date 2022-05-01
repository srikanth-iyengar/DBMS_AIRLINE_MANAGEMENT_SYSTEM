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
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.Enquiry.Query;
import com.example.demo.Enquiry.QueryRepository;
import com.example.demo.User.CustomerRepository;
import com.example.demo.User.CustomerService;
import com.example.demo.User.User;

@Controller
@RequestMapping("/admin")
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;
	
	@Autowired
	public QueryRepository queryRepository;
	
	@Autowired
	public CustomerService customerService;
	
	@Autowired
	public CustomerRepository customerRepository;
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
		
		List<User> emp = customerRepository.findAll();
		List<User> ll = new ArrayList<User>();
		for(User pp : emp){
			if(pp.getLocked()) {
				ll.add(pp);
			}
		}
		m.addAttribute("employee", e);
		m.addAttribute("lock", ll);
		m.addAttribute("list", thisadmin);
		return "adminpage";
	}
	
	@GetMapping("/login")
	public String adminlogin() {
		return "adminlogin";
	}
	
	
	@GetMapping("/home/{id}")
	public RedirectView admin(@PathVariable String id) {
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
		RedirectView rv = new RedirectView();
		rv.setUrl("http://localhost:8080/admin/home");
		return rv;
	}
	
	@GetMapping("home/unlock/{username}")
	public RedirectView unlock(@PathVariable String username) {
		RedirectView rv = new RedirectView();
		StringBuilder uuid = new StringBuilder(username)	;
		Long uid = Long.parseLong(uuid.substring(2));
		User u = customerRepository.findById(uid).get();
		u.setLocked(false);
		customerRepository.save(u);
		rv.setUrl("http://localhost:8080/admin/home");
		return rv;
	}
}
