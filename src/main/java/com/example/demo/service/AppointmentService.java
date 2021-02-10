package com.example.demo.service;

import com.example.demo.exceptions.AppointmentNotExists;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Appointment activateAppointment(int id) throws AppointmentNotExists {
        Appointment appointment = null;
        if(appointmentRepository.existsById(id))
        {
            appointment = appointmentRepository.activateAppointment(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new AppointmentNotExists("Couldn't activate, that appointment doesn't exists."));
    }

    public Appointment cancelAppointment(int id) throws AppointmentNotExists {
        Appointment appointment = null;
        if(appointmentRepository.existsById(id))
        {
            appointment = appointmentRepository.cancelAppointment(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new AppointmentNotExists("Couldn't cancel, that appointment doesn't exists."));
    }

    public Appointment postponeAppointment(int id) throws AppointmentNotExists {
        Appointment appointment = null;
        if(appointmentRepository.existsById(id))
        {
            appointment = appointmentRepository.postponeAppointment(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new AppointmentNotExists("Couldn't postpone, that appointment doesn't exists."));
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
