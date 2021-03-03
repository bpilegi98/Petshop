package com.example.demo.controller;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerDeleteTest {

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mockMvc;

    Person person;
    String listJson;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void deletePersonOkTest() throws Exception {
        person = new Person(1, "Maria", "Magdalena", "444", "2222", null, null, null);


        when(personService.deletePerson("444")).thenReturn(person);
        when(personService.getByDni("444")).thenReturn(person);

        mockMvc.perform(delete("/person/{dni}", "444"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("The person has been deleted successfully."));
    }

    @Test
    public void deletePersonNotExistsTest() throws Exception {
        mockMvc.perform(delete("/person/{dni}", "444"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Couldn't delete that person."));
    }
}
