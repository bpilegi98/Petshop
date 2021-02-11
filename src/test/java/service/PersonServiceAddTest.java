package service;


import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.model.Person;
import com.example.demo.model.enums.PersonType;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class PersonServiceAddTest {

    @Autowired
    PersonService personService;

    @Mock
    PersonRepository personRepository;
    Person person;

    @Before("")
    public void setUp()
    {
        personService = new PersonService(personRepository);
        person = new Person(1,"Bianca", "Pilegi", "11111111", "222222222", PersonType.CUSTOMER, null, null);
    }

    @Test
    public void addPersonOkTest() throws PetshopAlreadyExistsException
    {
        when(personService.addPerson(person)).thenReturn(person);
        Person personResult = new Person();
        personResult.setPersonType(PersonType.CUSTOMER);
        personResult.setDni("11111111");
        assertEquals(person.getPersonType(), personResult.getPersonType());
        assertEquals(person.getDni(), personResult.getDni());
    }

    @Test
    public void addPersonAlredyExistsTest() throws PetshopAlreadyExistsException
    {
        when(personService.addPerson(person)).thenThrow(new PetshopAlreadyExistsException("Couldn't add, that person already exists."));
        personService.addPerson(person);
    }
}
