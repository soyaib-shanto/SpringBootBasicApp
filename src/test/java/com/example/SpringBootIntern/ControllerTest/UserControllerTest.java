package com.example.SpringBootIntern.ControllerTest;

import com.example.SpringBootIntern.Controller.UserController;

import com.example.SpringBootIntern.Model.User;
import com.example.SpringBootIntern.Repository.UserRepository;
import com.example.SpringBootIntern.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a public endpoint"));
    }

    @Test
    void testUserEndpoint() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a user endpoint"));
    }

    @Test
    void testAdminEndpoint() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is an admin endpoint"));
    }

    @Test
    void testCreateUser_Success() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password123");
        user.setRole("USER");

        when(userRepository.existsByUsername("john")).thenReturn(false);
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User created successfully"));

        verify(userRepository, times(1)).existsByUsername("john");
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateUser_UsernameTaken() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password123");
        user.setRole("USER");

        when(userRepository.existsByUsername("john")).thenReturn(true);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username is taken"));

        verify(userRepository, times(1)).existsByUsername("john");
        verify(userService, never()).createUser(any());
    }
}
