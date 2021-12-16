package com.example.demo.User.Passenger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, String>{
//	List<Passenger> findByBookingId(String id);
}
