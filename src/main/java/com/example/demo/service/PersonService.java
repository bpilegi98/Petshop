package com.example.demo.service;

import com.example.demo.exceptions.PersonAlreadyExists;
import com.example.demo.model.Person;
import com.example.demo.projections.PersonQuantity;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll(String dni)
    {
        if(isNull(dni))
        {
            return personRepository.findAll();
        }
        return personRepository.findByDni(dni);
    }

    public void addPerson(Person newPerson)
    {
        personRepository.save(newPerson);

    }

    public List<PersonQuantity> getPersonQuantity()
    {
        return personRepository.getPersonQuantity();
    }
}
