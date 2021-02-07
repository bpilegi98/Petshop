package com.example.demo.service;

import com.example.demo.exceptions.PersonAlreadyExists;
import com.example.demo.exceptions.PersonNotExists;
import com.example.demo.model.Person;
import com.example.demo.projections.PersonQuantity;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Person addPerson(Person newPerson) throws PersonAlreadyExists
    {
        Person person = personRepository.save(newPerson);
        return Optional.ofNullable(person).orElseThrow(()-> new PersonAlreadyExists("The person you're trying to add already exists."));

    }

    public Person deletePerson(String dni) throws PersonNotExists
    {
        Person person = personRepository.delete(dni);
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExists("Couldn't delete, that person doesn't exists."));
    }

    public Person hireAsEmployee(String dni) throws PersonNotExists {
        Person person = personRepository.hireAsEmployee(dni);
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExists("Couldn't hire, that person doesn't exists."));
    }

    public Person hireAsVet(String dni) throws PersonNotExists {
        Person person = personRepository.hirePersonAsVet(dni);
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExists("Couldn't hire, that person doesn't exists."));
    }

    public Person firePerson(String dni) throws PersonNotExists {
        Person person = personRepository.firePerson(dni);
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExists("Couldn't fire, that person doesn't exists."));
    }

    public PersonQuantity getPersonQuantity()
    {
        return personRepository.getPersonQuantity();
    }
}
