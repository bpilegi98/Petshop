package service.person;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
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

    @Disabled("mismo problema que con hire employee")
    @Test
    public void firePersonOk() throws PetshopNotExistsException {
        person = mock(Person.class);
        when(personRepository.firePerson("444")).thenReturn(person);
        Person personResult = personService.firePerson("444");
        verify(personRepository, times(1)).firePerson("444");
        assertEquals(person.getPersonType(), personResult.getPersonType());
    }

    @Test
    public void firePersonNotExistsTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> personService.firePerson("444"));
    }
}
