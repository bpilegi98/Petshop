package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

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

    public Optional<Appointment> getById(int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = null;
        if(!isNull(id))
        {
            appointment = appointmentRepository.findById(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new PetshopNotExistsException("Couldn't find any appointment with that id."));
    }

    public Appointment addAppointment(Appointment newAppointment)
    {
        Appointment appointment = null;
        appointment = appointmentRepository.save(newAppointment);
        return Optional.ofNullable(appointment).orElseThrow(() -> new IllegalArgumentException("Couldn't add that appointment."));
    }

    public Optional<Appointment> activateAppointment(int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = null;
        if(appointmentRepository.existsById(id))
        {
            appointment = appointmentRepository.findById(id);
            appointmentRepository.activateAppointment(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new PetshopNotExistsException("Couldn't activate, that appointment doesn't exists."));
    }

    public Optional<Appointment> cancelAppointment(int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = null;
        if(appointmentRepository.existsById(id))
        {
            appointment = appointmentRepository.findById(id);
            appointmentRepository.cancelAppointment(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new PetshopNotExistsException("Couldn't cancel, that appointment doesn't exists."));
    }

    public Optional<Appointment> postponeAppointment(int id) throws PetshopNotExistsException {
        Optional<Appointment> appointment = null;
        if(appointmentRepository.existsById(id))
        {
            appointment = appointmentRepository.findById(id);
            appointmentRepository.postponeAppointment(id);
        }
        return Optional.ofNullable(appointment).orElseThrow(() -> new PetshopNotExistsException("Couldn't postpone, that appointment doesn't exists."));
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
