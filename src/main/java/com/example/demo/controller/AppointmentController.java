package com.example.demo.controller;


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
}
