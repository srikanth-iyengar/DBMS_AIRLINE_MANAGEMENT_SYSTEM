package com.example.demo.Employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RegistrationAdmin {
	private String firstName;
	private String lastName;
	private Integer age;
	private String password;
	private Double salary;
	private Character gender;
	private Department department;
}
