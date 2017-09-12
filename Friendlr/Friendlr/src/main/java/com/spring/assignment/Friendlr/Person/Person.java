package com.spring.assignment.Friendlr.Person;

public class Person {

	private Long ID;
	private String firstName;
	private String lastName;
		
	
	public Person(Long iD, String firstName, String lastName) {
		super();
		this.ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Person() {
		super();
	}

	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		this.ID = iD;
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
}
