package com.example.demo.service;

import com.example.demo.exceptions.PetshopNotExistsException;
import com.example.demo.model.Appointment;
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

public class AppointmentServiceGetByIdTest {

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
    public void getByIdOkTest() throws PetshopNotExistsException {
        appointment = new Appointment(1, null, null, null, null, null);
        when(appointmentRepository.findById(1)).thenReturn(Optional.ofNullable(appointment));
        Optional<Appointment> appointmentResult = appointmentService.getById(1);
        assertEquals(appointment, appointmentResult.get());
        verify(appointmentRepository, times(1)).findById(1);
    }

    @Test
    public void getByIdNotExistsExceptionTest()
    {
        assertThrows(PetshopNotExistsException.class, () -> appointmentService.getById(1));
    }
}
