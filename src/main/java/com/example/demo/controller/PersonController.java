package com.example.demo.controller;

import com.example.demo.exceptions.PetshopAlreadyExistsException;
import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Person;
import com.example.demo.projections.PersonQuantity;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addPerson(@RequestBody Person newPerson) throws PetshopAlreadyExistsException {
        Person person = personService.addPerson(newPerson);
        return (isNull(person)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't add that person.") :
                ResponseEntity.status(HttpStatus.CREATED).body("The person was created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll(@PathVariable(required = false) String dni)
    {
        return (isNull(dni)) ?
                ResponseEntity.ok(personService.getAll(null)) :
                ResponseEntity.ok(personService.getAll(dni));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<String> deletePerson(@PathVariable String dni) throws PetshopNotExistsException
    {
        Person person = personService.deletePerson(dni);
        return (isNull(person)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't delete that person.") :
                ResponseEntity.status(HttpStatus.OK).body("The person has been deleted successfully.");
    }

    @PutMapping("/hireEmployee/{dni}")
    public ResponseEntity<String> hireAsEmployee(@PathVariable String dni) throws PetshopNotExistsException
    {
        Person person = personService.hireAsEmployee(dni);
        return (isNull(person)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't hire that person.") :
                ResponseEntity.status(HttpStatus.OK).body("The employee has been hired successfully.");
    }

    @PutMapping("/hireVet/{dni}")
    public ResponseEntity<String> hireAsVet(@PathVariable String dni) throws PetshopNotExistsException
    {
        Person person = personService.hireAsVet(dni);
        return (isNull(person)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't hire that person.") :
                ResponseEntity.status(HttpStatus.OK).body("The employee has been hired successfully.");
    }

    @PutMapping("/firePerson/{dni}")
    public ResponseEntity<String> firePerson(@PathVariable String dni) throws PetshopNotExistsException
    {
        Person person = personService.firePerson(dni);
        return (isNull(person)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't hire that person.") :
                ResponseEntity.status(HttpStatus.OK).body("The employee has been hired successfully.");
    }

    @GetMapping("/projection")
    public ResponseEntity<PersonQuantity> getPersonQuantity()
    {
        return ResponseEntity.ok(personService.getPersonQuantity());
    }


}
