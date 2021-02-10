package com.example.demo.controller;


import com.example.demo.exceptions.AppointmentNotExists;
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
        appointmentService.addAppointment(newAppointment);
        return ResponseEntity.status(HttpStatus.CREATED).body("The person was created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Appointment>> getAll()
    {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Appointment> activateAppointment(@PathVariable int id) throws AppointmentNotExists {
        return ResponseEntity.ok(appointmentService.activateAppointment(id));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable int id) throws AppointmentNotExists {
        return ResponseEntity.ok(appointmentService.cancelAppointment(id));
    }

    @PutMapping("/postpone/{id}")
    public ResponseEntity<Appointment> postponeAppointment(@PathVariable int id) throws AppointmentNotExists {
        return ResponseEntity.ok(appointmentService.postponeAppointment(id));
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
