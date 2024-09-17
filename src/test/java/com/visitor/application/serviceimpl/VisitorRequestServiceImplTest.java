package com.visitor.application.serviceimpl;

import com.visitor.application.model.Employee;
import com.visitor.application.model.VisitorRequest;
import com.visitor.application.repository.VisitorRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitorRequestServiceImplTest {

    @InjectMocks
    private VisitorRequestServiceImpl visitorRequestService;

    @Mock
    private VisitorRequestRepository visitorRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveVisitorRequest_shouldReturnSavedVisitorRequest() {
        VisitorRequest visitorRequest = new VisitorRequest(1, "Nandha", "nandha@example.com", "1234567890",
                new byte[0], new Employee(), "Pending", new Timestamp(new Date().getTime()), "Business Meeting");
        when(visitorRequestRepository.save(visitorRequest)).thenReturn(visitorRequest);

        VisitorRequest savedVisitorRequest = visitorRequestService.saveVisitorRequest(visitorRequest);

        assertNotNull(savedVisitorRequest);
        assertEquals("Nandha", savedVisitorRequest.getVisitorName());
        verify(visitorRequestRepository, times(1)).save(visitorRequest);
    }

    @Test
    void updateVisitorRequest_shouldReturnUpdatedVisitorRequest() {
        VisitorRequest existingVisitorRequest = new VisitorRequest(1, "Nandha", "nandha@example.com", "1234567890",
                new byte[0], new Employee(), "Pending", new Timestamp(new Date().getTime()), "Business Meeting");
        VisitorRequest updatedVisitorRequest = new VisitorRequest(1, "Suriya", "suriya@example.com", "0987654321",
                new byte[0], new Employee(), "Approved", new Timestamp(new Date().getTime()), "Annual Meeting");

        when(visitorRequestRepository.findById(1)).thenReturn(existingVisitorRequest);
        when(visitorRequestRepository.update(updatedVisitorRequest)).thenReturn(updatedVisitorRequest);

        VisitorRequest result = visitorRequestService.updateVisitorRequest(1, updatedVisitorRequest);

        assertNotNull(result);
        assertEquals("Suriya", result.getVisitorName());
        assertEquals("Approved", result.getStatus());
        verify(visitorRequestRepository, times(1)).update(updatedVisitorRequest);
    }

    @Test
    void updateVisitorRequest_shouldReturnNullIfNotFound() {
        VisitorRequest visitorRequest = new VisitorRequest(1, "Nandha", "nandha@example.com", "1234567890",
                new byte[0], new Employee(), "Pending", new Timestamp(new Date().getTime()), "Business Meeting");

        when(visitorRequestRepository.findById(1)).thenReturn(null);

        VisitorRequest result = visitorRequestService.updateVisitorRequest(1, visitorRequest);

        assertNull(result);
    }

    @Test
    void deleteVisitorRequest_shouldCallDeleteById() {
        doNothing().when(visitorRequestRepository).deleteById(1);
        when(visitorRequestRepository.findById(1)).thenReturn(new VisitorRequest());

        visitorRequestService.deleteVisitorRequest(1);

        verify(visitorRequestRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteVisitorRequest_shouldThrowExceptionIfNotFound() {
        when(visitorRequestRepository.findById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            visitorRequestService.deleteVisitorRequest(1);
        });

        assertEquals("VisitorRequest with ID 1 does not exist.", exception.getMessage());
    }

    @Test
    void getVisitorRequestById_shouldReturnVisitorRequest() {
        VisitorRequest visitorRequest = new VisitorRequest(1, "Nandha", "suriya@example.com", "1234567890",
                new byte[0], new Employee(), "Pending", new Timestamp(new Date().getTime()), "Business Meeting");
        when(visitorRequestRepository.findById(1)).thenReturn(visitorRequest);

        VisitorRequest result = visitorRequestService.getVisitorRequestById(1);

        assertNotNull(result);
        assertEquals("Nandha", result.getVisitorName());
    }

    @Test
    void getAllVisitorRequests_shouldReturnListOfVisitorRequests() {
        List<VisitorRequest> visitorRequests = new ArrayList<>();
        visitorRequests.add(new VisitorRequest(1, "Nandha", "nandha@example.com", "1234567890",
                new byte[0], new Employee(), "Pending", new Timestamp(new Date().getTime()), "Business Meeting"));
        visitorRequests.add(new VisitorRequest(2, "Suriya", "suriya@example.com", "0987654321",
                new byte[0], new Employee(), "Approved", new Timestamp(new Date().getTime()), "Annual Meeting"));

        when(visitorRequestRepository.findAll()).thenReturn(visitorRequests);

        List<VisitorRequest> result = visitorRequestService.getAllVisitorRequests();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
