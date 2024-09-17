package com.visitor.application.serviceimpl;

import com.visitor.application.model.Visit;
import com.visitor.application.repository.VisitRepository;
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

class VisitServiceImplTest {

    @InjectMocks
    private VisitServiceImpl visitService;

    @Mock
    private VisitRepository visitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveVisit_shouldReturnSavedVisit() {
        Visit visit = new Visit(1, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                "checkIn", "checkOut", "status", "type", "blockStatus", 100);
        when(visitRepository.save(visit)).thenReturn(visit);

        Visit savedVisit = visitService.saveVisit(visit);

        assertNotNull(savedVisit);
        assertEquals("checkIn", savedVisit.getCheckIn());
        verify(visitRepository, times(1)).save(visit);
    }

    @Test
    void getVisitById_shouldReturnVisit() {
        Visit visit = new Visit(1, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                "checkIn", "checkOut", "status", "type", "blockStatus", 100);
        when(visitRepository.findById(1)).thenReturn(visit);

        Visit foundVisit = visitService.getVisitById(1);

        assertNotNull(foundVisit);
        assertEquals("checkIn", foundVisit.getCheckIn());
    }

    @Test
    void updateVisit_shouldReturnUpdatedVisit() {
        Visit existingVisit = new Visit(1, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                        new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                        "checkIn", "checkOut", "status", "type", "blockStatus", 100);
        Visit updatedVisit = new Visit(1, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                        new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                        "updatedCheckIn", "updatedCheckOut", "updatedStatus", "updatedType", "updatedBlockStatus", 200);

        when(visitRepository.update(updatedVisit)).thenReturn(updatedVisit);
        when(visitRepository.findById(1)).thenReturn(existingVisit);

        Visit result = visitService.updateVisit(updatedVisit);

        assertNotNull(result);
        assertEquals("updatedCheckIn", result.getCheckIn());
        verify(visitRepository, times(1)).update(updatedVisit);
    }

    @Test
    void deleteVisitById_shouldCallDeleteById() {
        doNothing().when(visitRepository).deleteById(1);
        when(visitRepository.findById(1)).thenReturn(new Visit(1, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                                                 new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                                                                 "checkIn", "checkOut", "status", "type", "blockStatus", 100));

        visitService.deleteVisitById(1);

        verify(visitRepository, times(1)).deleteById(1);
    }

    @Test
    void getAllVisits_shouldReturnListOfVisits() {
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit(1, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                              new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                              "checkIn", "checkOut", "status", "type", "blockStatus", 100));
        visits.add(new Visit(2, null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                              new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 
                              "checkIn2", "checkOut2", "status2", "type2", "blockStatus2", 200));

        when(visitRepository.findAll()).thenReturn(visits);

        List<Visit> result = visitService.getAllVisits();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
