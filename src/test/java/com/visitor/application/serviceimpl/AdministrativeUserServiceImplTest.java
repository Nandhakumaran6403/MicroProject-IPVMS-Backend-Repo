package com.visitor.application.serviceimpl;

import com.visitor.application.model.AdministrativeUser;
import com.visitor.application.repository.AdministrativeUserRepository;
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

class AdministrativeUserServiceImplTest {

    @InjectMocks
    private AdministrativeUserServiceImpl administrativeUserService;

    @Mock
    private AdministrativeUserRepository administrativeUserRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAdministrativeUser_shouldReturnSavedUser() {
        AdministrativeUser user = new AdministrativeUser(1, "Nandha", "nandha@example.com", "password123", "ADMIN", null, new Date());
        when(administrativeUserRepository.save(user)).thenReturn(user);

        AdministrativeUser savedUser = administrativeUserService.saveAdministrativeUser(user);

        assertNotNull(savedUser);
        assertEquals("Nandha", savedUser.getUserName());
        verify(administrativeUserRepository, times(1)).save(user);
    }


    @Test
    void deleteAdministrativeUser_shouldDeleteUser() {
        when(administrativeUserRepository.findById(1)).thenReturn(new AdministrativeUser());

        administrativeUserService.deleteAdministrativeUser(1);

        verify(administrativeUserRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteAdministrativeUser_shouldThrowExceptionIfUserNotFound() {
        when(administrativeUserRepository.findById(1)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            administrativeUserService.deleteAdministrativeUser(1);
        });

        assertEquals("AdministrativeUser with ID 1 does not exist.", exception.getMessage());
    }

    @Test
    void getAdministrativeUserById_shouldReturnUser() {
        AdministrativeUser user = new AdministrativeUser(1, "Nandha", "nandha@example.com", "password123", "ADMIN", null, new Date());
        when(administrativeUserRepository.findById(1)).thenReturn(user);

        AdministrativeUser foundUser = administrativeUserService.getAdministrativeUserById(1);

        assertNotNull(foundUser);
        assertEquals("Nandha", foundUser.getUserName());
    }

    @Test
    void getAllAdministrativeUsers_shouldReturnListOfUsers() {
        List<AdministrativeUser> userList = new ArrayList<>();
        userList.add(new AdministrativeUser(1, "Nandha", "nandha@example.com", "password123", "ADMIN", null, new Date()));
        userList.add(new AdministrativeUser(2, "Suriya", "suriya@example.com", "password123", "ITPARKADMIN", null, new Date()));
        when(administrativeUserRepository.findAll()).thenReturn(userList);

        List<AdministrativeUser> allUsers = administrativeUserService.getAllAdministrativeUsers();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }
}
