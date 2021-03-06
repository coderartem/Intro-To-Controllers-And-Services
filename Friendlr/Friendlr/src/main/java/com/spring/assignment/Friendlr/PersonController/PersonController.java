package com.spring.assignment.Friendlr.PersonController;

import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.assignment.Friendlr.Person.Person;
import com.spring.assignment.Friendlr.PersonDto.PersonDto;
import com.spring.assignment.Friendlr.PersonService.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private PersonService personService;
	
	PersonController(PersonService personService, @Qualifier("secondBean") String lol){
		this.personService = personService;
		System.out.println(lol);
	}
	
	@GetMapping
	public Set<PersonDto> getAllPersons(){
		return personService.getEverybody();
	}
	
	@GetMapping("{id}")
	public PersonDto getThatPerson(@PathVariable Long id, HttpServletResponse response){
			PersonDto person = personService.getThatPerson(id);
			if(person==null){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		return person;
	}
	
	@PostMapping
	public Set<PersonDto> addPerson(@RequestBody PersonDto person, HttpServletResponse response){
			Set<PersonDto> personsList = personService.addPerson(person);
			response.setStatus(personsList!=null? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_NOT_FOUND);
		return personsList;
	}
	
	@PutMapping("{id}")
	public Set<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto person, HttpServletResponse response){
			Set<PersonDto> personsList = personService.updatePerson(id, person);
			if(personsList==null){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		return personsList;
	}
	
	@DeleteMapping("{id}")
	public Set<PersonDto> deletePerson(@PathVariable Long id, HttpServletResponse response){
			Set<PersonDto> personsList = personService.deletePerson(id);
			if(personsList==null){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		return personsList;
	}
	
	@GetMapping("{id}/buddies")
	public Set<PersonDto> getBuddies(@PathVariable Long id, HttpServletResponse response){
		Set<PersonDto> personsList = personService.getFriends(id);
		if(personsList==null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return personsList;
	}
	
	@GetMapping("{id}/buddies/{buddyId}")
	public PersonDto GetThatFriend(@PathVariable Long id, @PathVariable Long buddyId, HttpServletResponse response){
		PersonDto person = personService.getThatFriend(id, buddyId);
		if(person==null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return person;
	}
	
	@PostMapping("{id}/buddies/{buddyId}")
	public Set<PersonDto> addFriendToThatPerson(@PathVariable Long id, @PathVariable Long buddyId, HttpServletResponse response){
		Set<PersonDto> personsList = personService.addFriendToThatPerson(id, buddyId);
		if(personsList==null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return personService.addFriendToThatPerson(id, buddyId);
	}
	
	@PutMapping("{id}/buddies/{buddyId}")
	public Set<PersonDto> updateFriend(@PathVariable Long id, @PathVariable Long buddyId, @RequestBody PersonDto person, HttpServletResponse response){
		Set<PersonDto> friendsList = personService.updateFriend(id, buddyId, person);
		if(friendsList==null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return friendsList;
	}
	
	@DeleteMapping("{id}/buddies/{buddyId}")
	public Set<PersonDto> deleteFriend(@PathVariable Long id, @PathVariable Long buddyId, HttpServletResponse response){
		Set<PersonDto> personsList = personService.deleteFromFriendsList(id,buddyId);
		if(personsList==null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return personsList;
	}
}
