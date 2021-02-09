package com.example.demo.controller;

import com.example.demo.model.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
