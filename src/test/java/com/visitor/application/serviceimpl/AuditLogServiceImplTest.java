package com.visitor.application.serviceimpl;

import com.visitor.application.model.AuditLog;
import com.visitor.application.repository.AuditLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditLogServiceImplTest {

    @InjectMocks
    private AuditLogServiceImpl auditLogService;

    @Mock
    private AuditLogRepository auditLogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAuditLog_shouldReturnSavedAuditLog() {
        AuditLog auditLog = new AuditLog(1, null, "LOGIN", new Date(), "User logged in");
        when(auditLogRepository.save(auditLog)).thenReturn(auditLog);

        AuditLog savedAuditLog = auditLogService.saveAuditLog(auditLog);

        assertNotNull(savedAuditLog);
        assertEquals("LOGIN", savedAuditLog.getAction());
        verify(auditLogRepository, times(1)).save(auditLog);
    }
   

    @Test
    void deleteAuditLog_shouldThrowExceptionIfLogNotFound() {
        when(auditLogRepository.findById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            auditLogService.deleteAuditLog(1);
        });

        assertEquals("AuditLog with ID 1 does not exist.", exception.getMessage());
    }

    @Test
    void getAuditLogById_shouldReturnAuditLog() {
        AuditLog auditLog = new AuditLog(1, null, "LOGIN", new Date(), "User logged in");
        when(auditLogRepository.findById(1)).thenReturn(auditLog);

        AuditLog foundAuditLog = auditLogService.getAuditLogById(1);

        assertNotNull(foundAuditLog);
        assertEquals("LOGIN", foundAuditLog.getAction());
    }

    @Test
    void getAllAuditLogs_shouldReturnListOfAuditLogs() {
        List<AuditLog> auditLogList = new ArrayList<>();
        auditLogList.add(new AuditLog(1, null, "LOGIN", new Date(), "User logged in"));
        auditLogList.add(new AuditLog(2, null, "LOGOUT", new Date(), "User logged out"));
        when(auditLogRepository.findAll()).thenReturn(auditLogList);

        List<AuditLog> allAuditLogs = auditLogService.getAllAuditLogs();

        assertNotNull(allAuditLogs);
        assertEquals(2, allAuditLogs.size());
    }
}
