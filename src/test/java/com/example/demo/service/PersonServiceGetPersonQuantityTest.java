package com.example.demo.service;

import com.example.demo.projections.PersonQuantity;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class PersonServiceGetPersonQuantityTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    PersonQuantity personQuantity;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    //Está bien testeado así la projection?
    @Test
    public void getPersonQuantityOkTest()
    {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        personQuantity = factory.createProjection(PersonQuantity.class);
        when(personRepository.getPersonQuantity()).thenReturn(personQuantity);
        PersonQuantity personQuantityResult = personService.getPersonQuantity();
        verify(personRepository, times(1)).getPersonQuantity();
        assertEquals(personQuantity, personQuantityResult);
    }

}
