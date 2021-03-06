package com.spring.assignment.Friendlr.Person;

import java.util.HashSet;
import java.util.Set;

public class Person {

	private Long ID;
	private String firstName;
	private String lastName;
	private Set<Person> buddies = new HashSet<>();
		
	
	public Person(Long iD, String firstName, String lastName, Set<Person> buddies) {
		super();
		this.ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.buddies = buddies;
	}
	
	public Person() {
		super();
	}

	public Set<Person> getBuddies() {
		return buddies;
	}

	public void setBuddies(Person friend) {
		buddies.add(friend);
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
