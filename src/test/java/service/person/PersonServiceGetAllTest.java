package service.person;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonServiceGetAllTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    List<Person> personList;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
    }

    @Test
    public void getAllOkTest()
    {
        personList = mock(List.class);
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> personListResult = personService.getAll(null);
        verify(personRepository, times(1)).findAll();
        assertEquals(personList, personListResult);
    }

    //CABIAR Y HACER UN METODO DE GET BY DNI SOLO SEPARADO DEL GET ALL
    /*
    @Disabled("Ver como pasar por parametro un dni que si coincida")
    @Test
    public void getByDniOkTest()
    {
        Person person = mock(Person.class);
        when(personRepository.findByDni("444")).thenReturn(Collections.singletonList(person));
        List<Person> personResult = personService.getAll("444");
        Person personAux = personResult.get(0);
        verify(personRepository, times(1)).findByDni("444");
        assertNotNull(personResult);
        assertEquals(person, personAux);
    }


    @Test
    public void getByDniNotExistsTest() throws PetshopNotExistsException
    {
        personList = new ArrayList<>();
        when(personRepository.findByDni("444")).thenReturn(personList);
        assertThrows(PetshopNotExistsException.class, () -> personService.getAll("444"));
    }
     */
}
