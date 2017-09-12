package com.spring.assignment.Friendlr.PersonService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.spring.assignment.Friendlr.Person.Person;

@Service
public class PersonService {
	
	
	Set<Person> persons = new HashSet<>();
	private Long index=(long) 0;
	
	
	
	
	public Set<Person> getListOfPersons() {
		return persons;
	}

	public Person getPerson(Long id) {
		
		for(Person person : persons){
			if(person.getID().equals(id)){
				return person;
			}
		}
		return null;
	}

	public Set<Person> addPerson(Person person) {
				person.setID(index++);
			return persons.add(person)?persons:null;
	}

	
	public Set<Person> updatePerson(Long id, Person person) {
			if(persons.removeIf(p->p.getID().equals(id))){
					person.setID(id);
					persons.add(person);
				return persons;
			}
		return null;
	}
	
	public Set<Person> deletePerson(Long id) {
		return persons.removeIf(p->p.getID().equals(id))?persons:null;
	}
	

}
