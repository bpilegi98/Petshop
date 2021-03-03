package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class AppointmentServiceAddTest {

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
    public void addAppointmentOkTest()
    {
        appointment = mock(Appointment.class);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        Appointment appointmentResult = appointmentService.addAppointment(appointment);
        assertEquals(appointment, appointmentResult);
        verify(appointmentRepository, times(1)).save(appointment);
    }
}
