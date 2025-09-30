package com.pitergomes.userbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitergomes.userbase.business.UserService;
import com.pitergomes.userbase.infrastructure.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("teste@email.com");
        user.setFullName("Teste Usuario");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setPhoneNumber("123456789");
        user.setPassword("senha123");
        user.setCreatedAt(LocalDateTime.now());
    }

    // ---- POST /user ----
    @Test
    void saveUser_shouldReturn201() throws Exception {
        Mockito.when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void saveUser_invalidEmail_shouldReturn400() throws Exception {
        user.setEmail("email-invalido");

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    // ---- GET /user ----
    @Test
    void findUserByEmail_shouldReturn200() throws Exception {
        Mockito.when(userService.findUserByEmail(eq(user.getEmail()))).thenReturn(user);

        mockMvc.perform(get("/user")
                        .param("email", user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.fullName").value(user.getFullName()));
    }

    @Test
    void findUserByEmail_notFound_shouldReturn404() throws Exception {
        Mockito.when(userService.findUserByEmail(eq("naoexiste@email.com")))
                .thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/user")
                        .param("email", "naoexiste@email.com"))
                .andExpect(status().isNotFound());
    }

    // ---- PUT /user ----
    @Test
    void updateUserById_shouldReturn200() throws Exception {
        Mockito.when(userService.updateUserById(eq(1L), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/user")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    void updateUserById_notFound_shouldReturn404() throws Exception {
        Mockito.when(userService.updateUserById(eq(99L), any(User.class)))
                .thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(put("/user")
                        .param("id", "99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNotFound());
    }

    // ---- DELETE /user ----
    @Test
    void deleteUserByEmail_shouldReturn200() throws Exception {
        mockMvc.perform(delete("/user")
                        .param("email", user.getEmail()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUserByEmail_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new NoSuchElementException("User not found"))
                .when(userService).deleteUserByEmail("naoexiste@email.com");

        mockMvc.perform(delete("/user")
                        .param("email", "naoexiste@email.com"))
                .andExpect(status().isNotFound());
    }
}
