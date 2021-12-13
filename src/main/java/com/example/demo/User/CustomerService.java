package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Employee.Employee;
import com.example.demo.Employee.EmployeeRepository;
@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = customerRepository.findByEmail(username);
		if(u == null) {
			Employee e = employeeRepository.findByUserName(username);
			return e;
		}
		return u;
	}
	
	public Long userId(String email) {
		User user_id = customerRepository.findByEmail(email);
		return user_id.getId();
	}
	
	public User currentUser(String email) {
		return customerRepository.findByEmail(email);
	}

	public String register(RegistrationRequest customer) {
		User cust = customerRepository.findByEmail(customer.getEmail());
		if (cust != null) {
			return "Email already registered";
		}
		BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();
		String password = passwordencoder.encode(customer.getPassword());
		System.out.println(customer.getPhoneNo());
		customerRepository
				.save(new User(
						customer.getFirstName(), 
						customer.getLastName(), 
						customer.getEmail(), 
						password, 
						customer.getGender(),
						customer.getPhoneNo()
				));
		return "User Successfully Register";
	}
}
