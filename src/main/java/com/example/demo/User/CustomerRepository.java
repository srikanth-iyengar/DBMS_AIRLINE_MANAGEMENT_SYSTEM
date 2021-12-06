package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}