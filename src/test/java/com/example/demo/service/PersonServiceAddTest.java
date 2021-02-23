package com.example.demo.service;


import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.model.Person;
import com.example.demo.model.enums.PersonType;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class PersonServiceAddTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    Person person;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
    }


    @Test
    public void addPersonOkTest() throws PetshopAlreadyExistsException {
        person = mock(Person.class);
        when(personRepository.save(person)).thenReturn(person);
        Person personResult = personService.addPerson(person);
        verify(personRepository, times(1)).save(person);
        assertEquals(person, personResult);
    }

    @Test
    public void addPersonAlredyExistsTest()
    {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", PersonType.CUSTOMER, null, null);
        assertThrows(PetshopAlreadyExistsException.class, () -> personService.addPerson(person));
    }
}
