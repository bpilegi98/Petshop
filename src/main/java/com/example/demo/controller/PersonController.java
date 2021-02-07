package com.example.demo.controller;

import com.example.demo.exceptions.PersonAlreadyExists;
import com.example.demo.exceptions.PersonNotExists;
import com.example.demo.model.Person;
import com.example.demo.projections.PersonQuantity;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<Person> addPerson(@RequestBody Person newPerson) throws PersonAlreadyExists {
        personService.addPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll(@RequestParam(required = false) String dni)
    {
        if(isNull(dni))
        {
            return ResponseEntity.ok(personService.getAll(null));
        }
        return ResponseEntity.ok(personService.getAll(dni));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Person> deletePerson(@RequestParam String dni) throws PersonNotExists
    {
        return ResponseEntity.ok(personService.deletePerson(dni));
    }

    @PutMapping("/hireEmployee/{dni}")
    public ResponseEntity<Person> hireAsEmployee(@RequestParam String dni) throws PersonNotExists
    {
        return ResponseEntity.ok(personService.hireAsEmployee(dni));
    }

    @PutMapping("/hireVet/{dni}")
    public ResponseEntity<Person> hireAsVet(@RequestParam String dni) throws PersonNotExists
    {
        return ResponseEntity.ok(personService.hireAsVet(dni));
    }

    @PutMapping("/firePerson/{dni}")
    public ResponseEntity<Person> firePerson(@RequestParam String dni) throws PersonNotExists
    {
        return ResponseEntity.ok(personService.firePerson(dni));
    }

    @GetMapping("/projection")
    public ResponseEntity<PersonQuantity> getPersonQuantity()
    {
        return ResponseEntity.ok(personService.getPersonQuantity());
    }


}
