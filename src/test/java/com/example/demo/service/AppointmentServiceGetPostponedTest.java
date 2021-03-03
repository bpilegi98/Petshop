package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class AppointmentServiceGetPostponedTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    List<Appointment> appointmentList;

    @BeforeEach
    public void setUp()
    {
        openMocks(this);
    }

    @Test
    public void getPostponedAppointmentsOkTest()
    {
        appointmentList = Collections.emptyList();
        when(appointmentRepository.getPostponedAppointments()).thenReturn(appointmentList);
        List<Appointment> listResult = appointmentService.getPostponedAppointments();
        assertEquals(appointmentList, listResult);
        verify(appointmentRepository, times(1)).getPostponedAppointments();
    }
}
