package com.example.demo.controller;

import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
import com.example.demo.projections.PersonQuantity;
import com.example.demo.service.PersonService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/person")
@Log
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addPerson(@RequestBody Person newPerson) throws PetshopAlreadyExistsException {
        ResponseEntity responseEntity = null;
        try
        {
            personService.addPerson(newPerson);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("The person was created successfully");
            log.log(Level.FINE, "Person added.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the dni number.");
            log.log(Level.WARNING, "Couldn't add person.");
        }
        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll(@PathVariable(required = false) String dni)
    {
        if(isNull(dni))
        {
            return ResponseEntity.ok(personService.getAll(null));
        }
        return ResponseEntity.ok(personService.getAll(dni));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Person> deletePerson(@PathVariable String dni) throws PetshopNotExistsException
    {
        ResponseEntity responseEntity = null;
        try {
            personService.deletePerson(dni);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The person has been deleted successfully.");
            log.log(Level.FINE, "Person deleted.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the dni number.");
            log.log(Level.WARNING, "Couldn't delete person.");
        }
        return responseEntity;
    }

    @PutMapping("/hireEmployee/{dni}")
    public ResponseEntity<Person> hireAsEmployee(@PathVariable String dni) throws PetshopNotExistsException
    {
        ResponseEntity responseEntity = null;
        try {
            personService.hireAsEmployee(dni);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The employee has been hired successfully.");
            log.log(Level.FINE, "Person updated.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the dni number.");
            log.log(Level.WARNING, "Couldn't update person.");
        }
        return responseEntity;
    }

    @PutMapping("/hireVet/{dni}")
    public ResponseEntity<Person> hireAsVet(@PathVariable String dni) throws PetshopNotExistsException
    {
        ResponseEntity responseEntity = null;
        try {
            personService.hireAsVet(dni);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The vet has been hired successfully.");
            log.log(Level.FINE, "Person updated.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the dni number.");
            log.log(Level.WARNING, "Couldn't update person.");
        }
        return responseEntity;
    }

    @PutMapping("/firePerson/{dni}")
    public ResponseEntity<Person> firePerson(@PathVariable String dni) throws PetshopNotExistsException
    {
        ResponseEntity responseEntity = null;
        try
        {
            personService.firePerson(dni);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The person has been fired successfully.");
            log.log(Level.FINE, "Person updated.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the dni number.");
            log.log(Level.WARNING, "Couldn't update person.");
        }
        return responseEntity;
    }

    @GetMapping("/projection")
    public ResponseEntity<PersonQuantity> getPersonQuantity()
    {
        return ResponseEntity.ok(personService.getPersonQuantity());
    }


}
