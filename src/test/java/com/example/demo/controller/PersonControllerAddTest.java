package com.example.demo.controller;

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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerAddTest {

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
        person = new Person(1, "Maria", "Magdalena", "444", "2222", null, null, null);
    }


    @Test
    public void addPersonOkTest() throws Exception {

        try
        {
            listJson = new ObjectMapper().writeValueAsString(person);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        when(personService.addPerson(person)).thenReturn(person);
        mockMvc.perform(post("/person/").contentType(MediaType.APPLICATION_JSON)
                .content(listJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("The person was created successfully"));
    }


    @Test
    public void addPersonAlreadyExistsExceptionTest() throws Exception {
        try
        {
            listJson = new ObjectMapper().writeValueAsString(person);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        
        mockMvc.perform(post("/person/").contentType(MediaType.APPLICATION_JSON)
                .content(listJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Couldn't add that person."));
    }
}
