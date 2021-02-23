package com.example.demo.controller;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Pet;
import com.example.demo.projections.PetWithOwner;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addPet(@RequestBody Pet newPet)
    {
        petService.addPet(newPet);
        return ResponseEntity.status(HttpStatus.CREATED).body("The pet was created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Pet>> getAll()
    {
        return ResponseEntity.ok(petService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pet>> getById(@PathVariable int id) throws PetshopNotExistsException {
        return (isNull(petService.getById(id))) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.ok(petService.getById(id));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<PetWithOwner> getPetWithOwner(@PathVariable int id) throws PetshopNotExistsException {
        return (isNull(petService.getPetWithOwner(id))) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.ok(petService.getPetWithOwner(id));
    }
}
