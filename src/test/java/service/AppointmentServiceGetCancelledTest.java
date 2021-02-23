package service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AppointmentServiceGetCancelledTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    List<Appointment> appointmentList;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
    }

    @Test
    public void getCancelledAppointmentsOkTest()
    {
        appointmentList = Collections.emptyList();
        when(appointmentRepository.getCancelledAppointments()).thenReturn(appointmentList);
        List<Appointment> listResult = appointmentService.getCancelledAppointments();
        assertEquals(appointmentList, listResult);
        verify(appointmentRepository, times(1)).getCancelledAppointments();
    }
}
