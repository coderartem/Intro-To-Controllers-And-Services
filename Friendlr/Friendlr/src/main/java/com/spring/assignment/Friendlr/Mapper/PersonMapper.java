package com.spring.assignment.Friendlr.Mapper;
import org.mapstruct.Mapper;

import com.spring.assignment.Friendlr.Person.Person;
import com.spring.assignment.Friendlr.PersonDto.PersonDto;

@Mapper(componentModel="spring")  
public interface PersonMapper {		
	PersonDto toPersonDto(Person person);  //name doesn't matter
	Person toPerson (PersonDto pDto);

}
