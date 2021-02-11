package com.example.demo.controller;


import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ResponseEntity responseEntity = null;
        try
        {
            appointmentService.addAppointment(newAppointment);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("The appointment was created successfully");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Make sure all data is filled in.");
        }
        return responseEntity;
    }

    @GetMapping("/")
    public ResponseEntity<List<Appointment>> getAll()
    {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Appointment> activateAppointment(@PathVariable int id) throws PetshopNotExistsException {
        ResponseEntity responseEntity = null;
        try {
            appointmentService.activateAppointment(id);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The appointment has been updated successfully.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the id.");
        }
        return responseEntity;
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable int id) throws PetshopNotExistsException {
        ResponseEntity responseEntity = null;
        try {
            appointmentService.cancelAppointment(id);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The appointment has been updated successfully.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the id.");
        }
        return responseEntity;
    }

    @PutMapping("/postpone/{id}")
    public ResponseEntity<Appointment> postponeAppointment(@PathVariable int id) throws PetshopNotExistsException {
        ResponseEntity responseEntity = null;
        try {
            appointmentService.postponeAppointment(id);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("The appointment has been updated successfully.");
        }
        catch (IllegalArgumentException e)
        {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include the id.");
        }
        return responseEntity;
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
