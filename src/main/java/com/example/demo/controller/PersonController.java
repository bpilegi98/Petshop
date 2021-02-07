package com.example.demo.controller;

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
    public ResponseEntity<Person> addPerson(@RequestBody Person newPerson)
    {
        personService.addPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll(String dni)
    {
        if(isNull(dni))
        {
            return ResponseEntity.ok(personService.getAll(null));
        }
        return ResponseEntity.ok(personService.getAll(dni));
    }

    @GetMapping("/projection")
    public ResponseEntity<List<PersonQuantity>> getPersonQuantity()
    {
        return ResponseEntity.ok(personService.getPersonQuantity());
    }


}
