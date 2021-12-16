package com.example.demo.User;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindForm {
	private String source;
	private String destination;
	private LocalDate arrival;
	private LocalDate departure;
}
