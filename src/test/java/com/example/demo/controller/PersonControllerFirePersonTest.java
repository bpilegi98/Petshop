package com.example.demo.controller;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerFirePersonTest {

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mockMvc;

    Person person;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void firePersonOkTest() throws Exception {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", null, null, null);

        when(personService.firePerson("444")).thenReturn(person);
        when(personService.getByDni("444")).thenReturn(person);

        mockMvc.perform(put("/person/firePerson/{dni}", "444"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("The employee has been hired successfully."));
    }

    @Test
    public void firePersonNotExistsTest() throws Exception {
        mockMvc.perform(put("/person/firePerson/{dni}", "444"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Couldn't hire that person."));
    }
}

