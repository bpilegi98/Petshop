package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PersonController.class)
public class PersonControllerGetAllTest {

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mockMvc;

    List<Person> personList;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
        personList = new ArrayList<>();
    }


    @Test
    public void getAllOkTest() throws Exception {
        personList.add(new Person());
        String listJson = "";

        try
        {
            listJson = new ObjectMapper().writeValueAsString(personList);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        when(personService.getAll()).thenReturn(personList);
        mockMvc.perform(get("/person/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(listJson));
    }

}
