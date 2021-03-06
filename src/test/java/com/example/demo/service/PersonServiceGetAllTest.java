package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class PersonServiceGetAllTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    List<Person> personList;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
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
