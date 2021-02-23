package com.example.demo.controller;


import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addAppointment(@RequestBody Appointment newAppointment)
    {
        Appointment appointment = appointmentService.addAppointment(newAppointment);
        return (isNull(appointment)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't add that appointment.") :
                ResponseEntity.status(HttpStatus.CREATED).body("The appointment was created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Appointment>> getAll()
    {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> getById(@PathVariable int id) throws PetshopNotExistsException {
        return (isNull(appointmentService.getById(id))) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.ok(appointmentService.getById(id));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateAppointment(@PathVariable int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = appointmentService.activateAppointment(id);
        return (isNull(appointment)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't activate that appointment.") :
                ResponseEntity.status(HttpStatus.OK).body("The appointment has been updated successfully.");
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = appointmentService.cancelAppointment(id);
        return (isNull(appointment)) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't cancel that appointment.") :
                ResponseEntity.status(HttpStatus.OK).body("The appointment has been updated successfully.");
    }

    @PutMapping("/postpone/{id}")
    public ResponseEntity<String> postponeAppointment(@PathVariable int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = appointmentService.postponeAppointment(id);
        return isNull(appointment) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't postpone that appointment.") :
                ResponseEntity.status(HttpStatus.OK).body("The appointment has been updated successfully.");
    }

    @GetMapping("/active")
    public ResponseEntity<List<Appointment>> getActiveAppointments()
    {
        List<Appointment> appointments = appointmentService.getActiveAppointments();
        if ((appointments.isEmpty())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/cancelled")
    public ResponseEntity<List<Appointment>> getCancelledAppointments()
    {
        List<Appointment> appointments = appointmentService.getCancelledAppointments();
        if ((appointments.isEmpty())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/postponed")
    public ResponseEntity<List<Appointment>> getPostponedAppointments()
    {
        List<Appointment> appointments = appointmentService.getPostponedAppointments();
        if ((appointments.isEmpty())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(appointments);
    }
}
