package com.spring.assignment.Friendlr.PersonController;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.assignment.Friendlr.Person.Person;
import com.spring.assignment.Friendlr.PersonService.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private PersonService personController;
	
	PersonController(PersonService personController){
		this.personController = personController;
	}
	
	
	@GetMapping()
	public Set<Person> getAllPersons(){
		return personController.getListOfPersons();
	}
	
	@GetMapping("{id}")
	public Person getThatPerson(@PathVariable Long id, HttpServletResponse response){
			Person person = personController.getPerson(id);
			if(person==null){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		return person;
	}
	
	@PostMapping()
	public Set<Person> addPerson(@RequestBody Person person, HttpServletResponse response){
			Set<Person> personsList = personController.addPerson(person);
			response.setStatus(personsList!=null? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_NOT_FOUND);
		return personsList;
	}
	
	@PutMapping("{id}")
	public Set<Person> updatePerson(@PathVariable Long id, @RequestBody Person person, HttpServletResponse response){
			Set<Person> personsList = personController.updatePerson(id, person);
			if(personsList==null){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		return personsList;
	}
	
	@DeleteMapping("{id}")
	public Set<Person> deletePerson(@PathVariable Long id, HttpServletResponse response){
			Set<Person> personsList = personController.deletePerson(id);
			if(personsList==null){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		return personsList;
	}

}
