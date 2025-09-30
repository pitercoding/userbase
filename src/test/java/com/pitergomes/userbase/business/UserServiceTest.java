package com.pitergomes.userbase.business;

import com.pitergomes.userbase.infrastructure.entities.User;
import com.pitergomes.userbase.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .fullName("John Doe")
                .email("john@example.com")
                .password("123456")
                .build();
    }

    @Test
    void testFindUserByEmail_Success() {
        when(repository.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        User found = userService.findUserByEmail("john@example.com");

        assertNotNull(found);
        assertEquals("John Doe", found.getFullName());
    }

    @Test
    void testFindUserByEmail_NotFound() {
        when(repository.findByEmail("jane@example.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userService.findUserByEmail("jane@example.com"));
    }

    @Test
    void testSaveUser() {
        when(repository.saveAndFlush(user)).thenReturn(user);

        User saved = userService.saveUser(user);

        assertEquals(user.getEmail(), saved.getEmail());
        verify(repository, times(1)).saveAndFlush(user);
    }
}
