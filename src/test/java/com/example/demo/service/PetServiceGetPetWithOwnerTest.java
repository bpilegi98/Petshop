package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.projections.PetWithOwner;
import com.example.demo.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class PetServiceGetPetWithOwnerTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    PetWithOwner petWithOwner;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void getPetWithOwnerOkTest() throws PetshopNotExistsException {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        petWithOwner = factory.createProjection(PetWithOwner.class);
        when(petRepository.getPetWithOwner(1)).thenReturn(petWithOwner);
        when(petRepository.existsById(1)).thenReturn(true);
        PetWithOwner petResult = petService.getPetWithOwner(1);
        assertEquals(petWithOwner, petResult);
        verify(petRepository, times(1)).getPetWithOwner(1);
    }

    @Test
    public void getPetWithOwnerNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> petService.getPetWithOwner(1));
    }
}
