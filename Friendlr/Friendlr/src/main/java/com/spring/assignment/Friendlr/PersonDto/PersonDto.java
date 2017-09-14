package com.spring.assignment.Friendlr.PersonDto;

public class PersonDto {
	
	
	
	private Long ID;
	private String firstName;
	private String lastName;
		
	
	public PersonDto(Long iD, String firstName, String lastName) {
		super();
		this.ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public PersonDto() {
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
