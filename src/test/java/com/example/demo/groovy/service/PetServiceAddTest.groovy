package com.example.demo.groovy.service

import com.example.demo.model.Pet
import com.example.demo.model.enums.PetType
import com.example.demo.repository.PetRepository
import com.example.demo.service.PetService
import org.junit.Before
import spock.lang.Specification

class PetServiceAddTest extends Specification{

    def petRepository
    def petService
    def pet

    @Before
    void setup()
    {
        //petRepository = new PetRepository()
        petService = new PetService(petRepository)
        pet = new Pet(1, "Rocco", 2, "Labrador", 25, PetType.DOG, null, null)
    }
}
