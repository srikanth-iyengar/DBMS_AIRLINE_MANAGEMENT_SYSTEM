package com.example.demo.Employee;

import java.util.Objects;

public class RegistrationAdmin {
	private String firstName;
	private String lastName;
	private Integer age;
	private String password;
	private Double salary;
	private Character gender;
	private Department department;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, department, firstName, gender, lastName, password, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationAdmin other = (RegistrationAdmin) obj;
		return Objects.equals(age, other.age) && department == other.department
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(salary, other.salary);
	}
	@Override
	public String toString() {
		return "RegistrationAdmin [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", password="
				+ password + ", salary=" + salary + ", gender=" + gender + ", department=" + department + "]";
	}
	
	
}
