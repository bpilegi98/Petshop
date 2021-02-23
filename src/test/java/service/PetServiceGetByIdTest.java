package service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Pet;
import com.example.demo.model.enums.PetType;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PetServiceGetByIdTest {

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
    public void getByIdOkTest() throws PetshopNotExistsException {
        pet = new Pet(1, "Rocco", 2, "Labrador", 45, PetType.DOG, null, null);
        when(petRepository.findById(1)).thenReturn(Optional.ofNullable(pet));
        Optional<Pet> petResult = petService.getById(1);
        assertEquals(pet, petResult.get());
        verify(petRepository, times(1)).findById(1);
    }

    @Test
    public void getByIdNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> petService.getById(1));
    }
}
