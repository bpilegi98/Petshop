package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class PetServiceGetAllTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    List<Pet> petList;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void getAllOkTest()
    {
        petList = Collections.emptyList();
        when(petRepository.findAll()).thenReturn(petList);
        List<Pet> listResult = petService.getAll();
        assertEquals(petList, listResult);
        verify(petRepository, times(1)).findAll();
    }
}