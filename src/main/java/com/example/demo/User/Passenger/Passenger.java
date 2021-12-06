package com.example.demo.User.Passenger;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.Booking.Booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
