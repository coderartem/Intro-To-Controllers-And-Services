package com.spring.assignment.Friendlr.PersonService;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.assignment.Friendlr.Mapper.PersonMapper;
import com.spring.assignment.Friendlr.Person.Person;
import com.spring.assignment.Friendlr.PersonDto.PersonDto;

@Service
public class PersonService {
	
	Set<Person> persons = new HashSet<>();
	private Long index=(long) 0;
	
	
	private PersonMapper personMapper;
	
	PersonService(PersonMapper personMapper){
		this.personMapper = personMapper;
	}
	
	
	public Set<PersonDto> getEverybody() {
		return convertPersonsToPersonsDto(persons);
	}

	public PersonDto getThatPerson(Long id) {
		for(Person person : persons){
			if(person.getID().equals(id)){
				return personMapper.toPersonDto(person);
			}
		}
		return null;
	}
	
	//This method add friends to Person
	public Person getPersonEntity(Long id) {
		for(Person person : persons){
			if(person.getID().equals(id)){
					return person;
			}
		}
		return null;
	}

	public Set<PersonDto> addPerson(PersonDto person) {
				person.setID(index++);				
			return persons.add(personMapper.toPerson(person))?convertPersonsToPersonsDto(persons):null;
	}

	public Set<PersonDto> updatePerson(Long id, PersonDto person) {
			if(persons.removeIf(p->p.getID().equals(id))){
					person.setID(id);
					persons.add(personMapper.toPerson(person));	
					for(Person prsn : persons){
						prsn.getBuddies().removeIf(p->p.getID().equals(id));
						prsn.setBuddies(personMapper.toPerson(person));
					}
				return convertPersonsToPersonsDto(persons);
			}
		return null;
	}
	
	public Set<PersonDto> deletePerson(Long id) {
		if(!persons.removeIf(p->p.getID().equals(id))){return null;}
		for(Person person : persons){
			person.getBuddies().removeIf(p->p.getID().equals(id));
		}
		return convertPersonsToPersonsDto(persons);
	}
	
	public Set<PersonDto> getFriends(Long id) {
		if(getPersonEntity(id)!=null){
			return convertPersonsToPersonsDto(getPersonEntity(id).getBuddies());
		}else return null;
	}
	
	public Set<PersonDto> addFriendToThatPerson(Long id, Long buddyId) {
		if(getPersonEntity(buddyId)!=null && getPersonEntity(id)!=null){
		getPersonEntity(id).setBuddies(getPersonEntity(buddyId));
		getPersonEntity(buddyId).setBuddies(getPersonEntity(id));
	return convertPersonsToPersonsDto(getPersonEntity(id).getBuddies());
	}else return null;
	}
	
	public Set<PersonDto> deleteFromFriendsList(Long id, Long buddyId) {
		if(getPersonEntity(id)==null) {return null;}
		return (getPersonEntity(id).getBuddies().removeIf(f->f.getID().equals(buddyId)) 
				&& getPersonEntity(buddyId).getBuddies().removeIf(f->f.getID().equals(id)))
				?convertPersonsToPersonsDto(getPersonEntity(id).getBuddies()):null;
	}
	
	public Set<PersonDto> updateFriend(Long id, Long buddyId, PersonDto person) {
		if(personAndFriendExist(id, buddyId)){
			updatePerson(buddyId,person);
			return convertPersonsToPersonsDto(getPersonEntity(id).getBuddies());
		}else return null;
	}
	
	public Set<PersonDto> convertPersonsToPersonsDto(Set<Person> persons){
		return persons.stream().map(personMapper::toPersonDto).collect(Collectors.toSet());
	}

	public boolean personAndFriendExist(Long id, Long buddyId){
		return (getPersonEntity(id)!=null && getPersonEntity(buddyId)!=null)?true:false;
	}
}
