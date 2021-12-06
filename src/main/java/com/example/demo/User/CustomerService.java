package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return customerRepository.findByEmail(username);
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
