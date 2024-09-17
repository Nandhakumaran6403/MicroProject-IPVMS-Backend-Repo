package com.visitor.application.serviceimpl;

import com.visitor.application.model.Appointment;
import com.visitor.application.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnSavedAppointment() {
        Appointment appointment = new Appointment(1, LocalDateTime.now(), "Nandha", "nandha@example.com", "1234567890", "Meeting", null);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment savedAppointment = appointmentService.save(appointment);

        assertNotNull(savedAppointment);
        assertEquals("Nandha", savedAppointment.getVisitorName());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void findById_shouldReturnAppointment() {
        Appointment appointment = new Appointment(1, LocalDateTime.now(), "Nandha", "nandha@example.com", "1234567890", "Meeting", null);
        when(appointmentRepository.findById(1)).thenReturn(appointment);

        Appointment foundAppointment = appointmentService.findById(1);

        assertNotNull(foundAppointment);
        assertEquals("Nandha", foundAppointment.getVisitorName());
    }


    @Test
    void delete_shouldCallDeleteMethod() {
        doNothing().when(appointmentRepository).delete(1);

        appointmentService.delete(1);

        verify(appointmentRepository, times(1)).delete(1);
    }

    @Test
    void findAll_shouldReturnListOfAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(1, LocalDateTime.now(), "Nandha", "nandha@example.com", "1234567890", "Meeting", null));
        appointmentList.add(new Appointment(2, LocalDateTime.now(), "Suriya", "suriya@example.com", "0987654321", "Consultation", null));
        when(appointmentRepository.findAll()).thenReturn(appointmentList);

        List<Appointment> allAppointments = appointmentService.findAll();

        assertNotNull(allAppointments);
        assertEquals(2, allAppointments.size());
    }
}
