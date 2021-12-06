package com.example.demo.Employee;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements UserDetailsService {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return employeeRepository.findByUserName(username);
	}

	public String registerAdmin(RegistrationAdmin admin) {
		SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
		String password = encoder.encode(admin.getPassword());
		System.out.println(admin.getSalary());
		employeeRepository.save(
			new Employee(
					Department.CUSTOMER_SERVICE, 
					admin.getSalary(), 
					admin.getAge(),
					admin.getFirstName(), 
					admin.getLastName(), 
					admin.getFirstName() + "." + admin.getLastName() + "." + 
							LocalDate.now().minusYears(admin.getAge()).getYear(),
					password,
					admin.getGender()
			)
		);
		return "Admin successfully registered";
	}
}
