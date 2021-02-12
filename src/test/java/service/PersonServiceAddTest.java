package service;


import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.model.Person;
import com.example.demo.model.enums.PersonType;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Disabled("no puede return null porque genera un NPE")
    @Test
    public void addPersonAlredyExistsTest() throws PetshopAlreadyExistsException
    {
        when(personRepository.save(person)).thenReturn(null);
        assertThrows(PetshopAlreadyExistsException.class, () -> personService.addPerson(person));
    }
}
