package com.example.demo.repository;

import com.example.demo.model.Pet;
import com.example.demo.projections.PetWithOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query(value = "select pt.name, ps.firstname, ps.lastname, ps.telephone " +
            "from pets pt inner join persons ps on pt.id_person = ps.id_person " +
            "where pt.id_pet = ?1", nativeQuery = true)
    PetWithOwner getPetWithOwner(int id);
}
