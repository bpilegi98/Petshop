package service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AppointmentServiceAddTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    Appointment appointment;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
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
