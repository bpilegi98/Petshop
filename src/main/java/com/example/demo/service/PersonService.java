package com.example.demo.service;

import com.example.demo.exceptions.PersonAlreadyExistsException;
import com.example.demo.exceptions.PersonNotExistsException;
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

    public Person addPerson(Person newPerson) throws PersonAlreadyExistsException
    {
        Person person = null;
        if(!personRepository.existsByDni(newPerson.getDni()))
        {
            person = personRepository.save(newPerson);
        }
        return Optional.ofNullable(person).orElseThrow(()-> new PersonAlreadyExistsException("The person you're trying to add already exists."));
    }

    public Person deletePerson(String dni) throws PersonNotExistsException
    {
        Person person = null;
        if(personRepository.existsByDni(dni))
        {
            person = personRepository.delete(dni);
        }
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExistsException("Couldn't delete, that person doesn't exists."));
    }

    public Person hireAsEmployee(String dni) throws PersonNotExistsException {
        Person person = null;
        if(personRepository.existsByDni(dni))
        {
            personRepository.hireAsEmployee(dni);
        }
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExistsException("Couldn't hire, that person doesn't exists."));
    }

    public Person hireAsVet(String dni) throws PersonNotExistsException {
        Person person = null;
        if(personRepository.existsByDni(dni))
        {
            personRepository.hirePersonAsVet(dni);
        }
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExistsException("Couldn't hire, that person doesn't exists."));
    }

    public Person firePerson(String dni) throws PersonNotExistsException {
        Person person = null;
        if(personRepository.existsByDni(dni))
        {
            personRepository.firePerson(dni);
        }
        return Optional.ofNullable(person).orElseThrow(()-> new PersonNotExistsException("Couldn't fire, that person doesn't exists."));
    }

    public PersonQuantity getPersonQuantity()
    {
        return personRepository.getPersonQuantity();
    }
}
