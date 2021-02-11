package service;

import com.example.demo.model.Person;
import com.example.demo.model.enums.PersonType;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonServiceGetAllTest {

    @Autowired
    PersonService personService;

    @Mock
    PersonRepository personRepository;
    List<Person> personList;

    @Before("")
    public void setUp()
    {
        personService = new PersonService(personRepository);
        Person person = new Person(1,"Bianca", "Pilegi", "11111111", "222222222", PersonType.CUSTOMER, null, null);
        Person person2 = new Person(1,"Maria", "Magdalena", "11111121", "222252222", PersonType.EMPLOYEE, null, null);
        personList.add(person);
        personList.add(person2);
    }

    @Test
    public void getAllOkTest()
    {
        when(personService.getAll(null)).thenReturn(personList);
        List<Person> personListResult = personService.getAll(null);
        assertEquals(2, personList.size());
        verify(personRepository, times(1)).findAll();
    }
}
