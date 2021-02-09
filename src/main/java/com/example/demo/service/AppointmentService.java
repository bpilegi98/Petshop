package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAll()
    {
        return appointmentRepository.findAll();
    }

    public void addAppointment(Appointment newAppointment)
    {
        appointmentRepository.save(newAppointment);
    }

    public List<Appointment> getActiveAppointments()
    {
        return appointmentRepository.getActiveAppointments();
    }

    public List<Appointment> getCancelledAppointments()
    {
        return appointmentRepository.getCancelledAppointments();
    }

    public List<Appointment> getPostponedAppointments()
    {
        return appointmentRepository.getPostponedAppointments();
    }
}
