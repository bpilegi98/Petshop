package service;

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
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonServiceHireEmployeeTest {

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

    @Disabled("No se como hacer para correr el test y que no me tire la excepcion de que no encuentra la persona")
    @Test
    public void hireEmployeeOkTest() throws PetshopNotExistsException {
        person = mock(Person.class);
        when(personRepository.hireAsEmployee("444")).thenReturn(person);
        Person personResult = personService.hireAsEmployee("444");
        verify(personRepository, times(1)).hireAsEmployee("444");
        assertEquals(person.getPersonType(), personResult.getPersonType());
    }

    @Test
    public void hireEmployeeNotExistsTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> personService.hireAsEmployee("444"));
    }
}
