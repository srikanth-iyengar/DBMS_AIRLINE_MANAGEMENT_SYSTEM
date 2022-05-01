package com.example.demo.User.Passenger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.Booking.Booking;

@Entity
public class Passenger {

	
	@Id
	@Column(name = "unique_id")
	private String uid;

	@Column(name = "aadhar_id", nullable = false, length = 12)
	private String aadhar;

	@Column(name = "firstName", nullable = false, length = 20)
	private String firstName;

	@Column(name = "lastName", nullable = false, length = 20)
	private String lastName;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "gender", nullable = false)
	private Character gender;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	

	public Passenger() {
		super();
	}

	public Passenger(String uid, String aadhar, String firstName, String lastName, int age, Character gender,
			Booking booking) {
		super();
		this.uid = uid;
		this.aadhar = aadhar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.booking = booking;
	}



	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public String getAadhar() {
		return aadhar;
	}



	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}



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



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public Character getGender() {
		return gender;
	}



	public void setGender(Character gender) {
		this.gender = gender;
	}



	public Booking getBooking() {
		return booking;
	}



	public void setBooking(Booking booking) {
		this.booking = booking;
	}



	public Passenger(String aadhar, String firstName, String lastName, int age, Character gender, String uid) {
		super();
		this.uid = uid;
		this.aadhar = aadhar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

}
