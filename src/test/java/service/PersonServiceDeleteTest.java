package service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
import com.example.demo.model.enums.PersonType;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonServiceDeleteTest {

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

    @Disabled("Not exists exception is thrown")
    @Test
    public void deletePersonOkTest() throws PetshopNotExistsException
    {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", null, null, null);
        when(personRepository.existsByDni("444")).thenReturn(true);
        when(personRepository.findByDni("444")).thenReturn(person);
        when(personRepository.delete("444")).thenReturn(person);
        Person personResult = personService.deletePerson("444");
        assertEquals(person, personResult);
        verify(personRepository, times(1)).delete("444");
    }

    @Test
    public void deleteNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> personService.deletePerson("444"));
    }
}
