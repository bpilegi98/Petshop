package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update appointments set status = 'ACTIVE' where id = ?1", nativeQuery = true)
    int activateAppointment(int id);

    @Modifying(clearAutomatically = true)
    @Query(value = "update appointments set status = 'CANCELLED' where id = ?1", nativeQuery = true)
    int cancelAppointment(int id);

    @Modifying(clearAutomatically = true)
    @Query(value = "update appointments set status = 'POSTPONED' where id = ?1", nativeQuery = true)
    int postponeAppointment(int id);

    @Query(value = "select * from appointments a where status = 'ACTIVE'", nativeQuery = true)
    List<Appointment> getActiveAppointments();

    @Query(value = "select * from appointments a where status = 'CANCELLED'", nativeQuery = true)
    List<Appointment> getCancelledAppointments();

    @Query(value = "select * from appointments a where status = 'POSTPONED'", nativeQuery = true)
    List<Appointment> getPostponedAppointments();
}
