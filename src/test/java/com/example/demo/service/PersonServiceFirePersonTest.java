package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
import com.example.demo.model.enums.PersonType;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonServiceFirePersonTest {

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
    public void firePersonOk() throws PetshopNotExistsException {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", PersonType.VET, null, null);
        when(personRepository.firePerson(person.getDni())).thenReturn(1);
        when(personRepository.findByDni(person.getDni())).thenReturn(new Person(1,"Maria", "Magdalena", "444", "2222", PersonType.CUSTOMER, null, null));
        Person personResult = personService.firePerson(person.getDni());
        verify(personRepository, times(1)).firePerson(person.getDni());
        assertEquals(PersonType.CUSTOMER, personResult.getPersonType());
    }

    @Test
    public void firePersonNotExistsTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> personService.firePerson("444"));
    }
}
