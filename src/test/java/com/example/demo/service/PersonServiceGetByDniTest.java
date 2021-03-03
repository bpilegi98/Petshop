package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
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
import static org.mockito.MockitoAnnotations.openMocks;

public class PersonServiceGetByDniTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    Person person;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }


    @Test
    public void getByDniOkTest() throws PetshopNotExistsException {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", PersonType.CUSTOMER, null, null);
        when(personRepository.findByDni("444")).thenReturn(person);
        Person personResult = personService.getByDni("444");
        assertEquals(person, personResult);
        verify(personRepository, times(1)).findByDni("444");
    }


    @Test
    public void getByDniNotExistsTest() throws PetshopNotExistsException
    {
        assertThrows(PetshopNotExistsException.class, () -> personService.getByDni("444"));
    }

}
