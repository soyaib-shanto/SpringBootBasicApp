package com.example.SpringBootIntern.ServicesTest;

import com.example.SpringBootIntern.services.UserService;

import com.example.SpringBootIntern.ExceptionHandler.ValidationException;
import com.example.SpringBootIntern.Model.User;
import com.example.SpringBootIntern.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private User createValidUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole("ADMIN");
        return user;
    }

    @Test
    void testCreateUser_Success() {
        User user = createValidUser();
        String encodedPass = "encodedPassword123";

        when(passwordEncoder.encode("password123")).thenReturn(encodedPass);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser);
        assertEquals(encodedPass, savedUser.getPassword());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals(encodedPass, captor.getValue().getPassword());
    }

    @Test
    void testCreateUser_PasswordNull_ThrowsException() {
        User user = createValidUser();
        user.setPassword(null);

        ValidationException ex = assertThrows(ValidationException.class, () -> userService.createUser(user));
        assertEquals("Password is required", ex.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testCreateUser_PasswordTooShort_ThrowsException() {
        User user = createValidUser();
        user.setPassword("123");

        ValidationException ex = assertThrows(ValidationException.class, () -> userService.createUser(user));
        assertEquals("Password must be between 6 and 30 characters", ex.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testCreateUser_PasswordTooLong_ThrowsException() {
        User user = createValidUser();
        user.setPassword("a".repeat(31));

        ValidationException ex = assertThrows(ValidationException.class, () -> userService.createUser(user));
        assertEquals("Password must be between 6 and 30 characters", ex.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testCreateUser_InvalidRole_ThrowsException() {
        User user = createValidUser();
        user.setRole("INVALID");

        ValidationException ex = assertThrows(ValidationException.class, () -> userService.createUser(user));
        assertEquals("Role should be ADMIN OR USER", ex.getMessage());
        verify(userRepository, never()).save(any());
    }
}
