package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.model.enums.PetType;
import com.example.demo.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PetServiceAddTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    Pet pet;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
    }

    @Test
    public void addPetOkTest()
    {
        pet = new Pet(1, "Rocco", 2, "Labrador", 45, PetType.DOG, null, null);
        when(petRepository.save(pet)).thenReturn(pet);
        Pet petResult = petService.addPet(pet);
        assertEquals(pet, petResult);
        verify(petRepository, times(1)).save(pet);
    }
}
