package service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
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
        personList = Collections.emptyList();
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> personListResult = personService.getAll();
        verify(personRepository, times(1)).findAll();
        assertEquals(personList, personListResult);
    }

}
