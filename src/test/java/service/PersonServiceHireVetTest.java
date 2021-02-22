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
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonServiceHireVetTest {

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
    public void hireVetOkTest() throws PetshopNotExistsException {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", PersonType.CUSTOMER, null, null);
        when(personRepository.hirePersonAsVet(person.getDni())).thenReturn(1);
        when(personRepository.existsByDni(person.getDni())).thenReturn(true);
        when(personRepository.findByDni(person.getDni())).thenReturn(new Person(1,"Maria", "Magdalena", "444", "2222", PersonType.VET, null, null));
        Person personResult = personService.hireAsVet(person.getDni());
        verify(personRepository, times(1)).hirePersonAsVet(person.getDni());
        assertEquals(PersonType.VET, personResult.getPersonType());
    }

    @Test
    public void hireVetNotExistsTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> personService.hireAsVet("444"));
    }
}
