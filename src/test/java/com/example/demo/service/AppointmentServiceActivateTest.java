package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Appointment;
import com.example.demo.model.enums.AppointmentStatus;
import com.example.demo.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class AppointmentServiceActivateTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    Appointment appointment;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void activateAppointmentOkTest() throws PetshopNotExistsException {
        appointment = new Appointment(1, null, null, AppointmentStatus.CANCELLED, null, null);
        when(appointmentRepository.existsById(1)).thenReturn(true);
        when(appointmentRepository.activateAppointment(1)).thenReturn(1);
        when(appointmentRepository.findById(1)).thenReturn(Optional.of(new Appointment(1, null, null, AppointmentStatus.ACTIVE, null, null)));
        Optional<Appointment> appointmentResult = appointmentService.activateAppointment(1);
        assertEquals(AppointmentStatus.ACTIVE, appointmentResult.get().getStatus());
        verify(appointmentRepository, times(1)).activateAppointment(1);
    }

    @Test
    public void activateAppointmentNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> appointmentService.activateAppointment(1));
    }
}
