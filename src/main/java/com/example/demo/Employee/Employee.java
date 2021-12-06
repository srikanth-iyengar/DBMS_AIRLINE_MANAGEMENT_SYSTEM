package com.example.demo.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Enquiry.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long id;

	@Column(name = "department",nullable = false)
	@Enumerated(EnumType.STRING)
	private Department department;
	
	@Column(name = "salary", nullable = false)
	private Double salary;
	
	@Column(name = "age", nullable = false)
	private Integer age;
	
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "is_locked", nullable = false)
	private Boolean locked = false;
	
	@Column(name = "is_enabled", nullable = false)
	private Boolean enabled = true;

	@Column(name = "gender", nullable = false, length = 1)
	private Character gender;
	
	@OneToMany(mappedBy = "employee")
	private Set<Query> queries;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Employee(
			Department department, 
			Double price, 
			Integer age, 
			String firstName, 
			String lastName,
			String userName, 
			String password,
			Character gender) {
		super();
		this.department = department;
		this.salary = price;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
	}

}
