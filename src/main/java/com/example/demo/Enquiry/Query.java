package com.example.demo.Enquiry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.Employee.Employee;
import com.example.demo.User.User;

@Entity
public class Query {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "query_id")
	private Long queryId;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "is_open", nullable = false)
	private Boolean isOpen;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
