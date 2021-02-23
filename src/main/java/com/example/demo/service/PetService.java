package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Pet;
import com.example.demo.projections.PetWithOwner;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAll()
    {
        return petRepository.findAll();
    }

    public Optional<Pet> getById(int id) throws PetshopNotExistsException {
        Optional<Pet> pet = null;
        if(!isNull(id))
        {
            pet = petRepository.findById(id);
        }
        return Optional.ofNullable(pet).orElseThrow(() -> new PetshopNotExistsException("Couldn't find a pet with that id."));
    }

    public void addPet(Pet newPet)
    {
        petRepository.save(newPet);
    }

    public PetWithOwner getPetWithOwner(int id)
    {
        return petRepository.getPetWithOwner(id);
    }
}
