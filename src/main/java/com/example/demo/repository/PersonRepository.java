package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.projections.PersonQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = "SELECT * FROM persons WHERE dni = ?1", nativeQuery = true)
    List<Person> findByDni(String dni);

    @Query(value = "DELETE * FROM persons WHERE dni = ?1", nativeQuery = true)
    Person delete(String dni);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE persons SET personType = 'EMPLOYEE' WHERE dni = ?1", nativeQuery = true)
    Person hireAsEmployee(String dni);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE persons SET personType = 'VET' WHERE dni = ?1", nativeQuery = true)
    Person hirePersonAsVet(String dni);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE persons SET personType = 'CUSTOMER' WHERE dni = ?1", nativeQuery = true)
    Person firePerson(String dni);

    @Query(value = "SELECT pr.firstname, pr.lastname, count(pt.id) as quantity FROM persons pr " +
            "INNER JOIN pets pt ON pr.id_person = pt.id_person " +
            "GROUP BY pr.id_person ", nativeQuery = true)
    PersonQuantity getPersonQuantity();

    boolean existsByDni(String dni);
}
