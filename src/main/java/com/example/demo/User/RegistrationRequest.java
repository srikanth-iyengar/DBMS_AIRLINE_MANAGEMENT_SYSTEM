package com.example.demo.User;

public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Character gender;
	private String phoneNo;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public RegistrationRequest(String firstName, String lastName, String password, String email, Character gender,
			String phoneNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.phoneNo = phoneNo;
	}
	public RegistrationRequest() {
		super();
	}
	
	
}