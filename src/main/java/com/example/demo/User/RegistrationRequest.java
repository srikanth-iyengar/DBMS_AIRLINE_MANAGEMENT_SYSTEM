package com.example.demo.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Character gender;
	private String phoneNo;
}