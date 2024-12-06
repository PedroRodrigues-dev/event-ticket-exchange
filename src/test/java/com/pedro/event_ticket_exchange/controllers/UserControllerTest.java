package com.pedro.event_ticket_exchange.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pedro.event_ticket_exchange.entities.User;
import com.pedro.event_ticket_exchange.services.UserService;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockitoBean
    private UserService userService;

    @Test
    void testGetAllUsersPaged() {
        List<User> mockUsers = Arrays.asList(
                createUser(1, "user1", "User", "One", "City1", "State1", "user1@example.com", "123456789"),
                createUser(2, "user2", "User", "Two", "City2", "State2", "user2@example.com", "987654321"));

        Page<User> mockPage = new PageImpl<>(mockUsers, PageRequest.of(0, 10), mockUsers.size());

        when(userService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<User> result = userController.getAllUsers(pageable);
        assertEquals(2, result.getContent().size());
        assertEquals("user1", result.getContent().get(0).getUsername());
        assertEquals("user2", result.getContent().get(1).getUsername());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetUserById_UserExists() {
        User mockUser = createUser(1, "user1", "User", "One", "City1", "State1", "user1@example.com", "123456789");
        when(userService.getById(1)).thenReturn(Optional.of(mockUser));

        ResponseEntity<User> response = userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        User body = Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new AssertionError("Response body should not be null"));
        assertEquals("user1", body.getUsername());
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private User createUser(Integer id, String username, String firstName, String lastName, String city, String state,
            String email, String phone) {
        User user = new User();
        user.setUserId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddressCity(city);
        user.setAddressState(state);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSports(true);
        user.setTheatre(false);
        user.setConcerts(true);
        user.setJazz(false);
        user.setClassical(true);
        user.setOpera(false);
        user.setRock(true);
        user.setVegas(false);
        user.setBroadway(true);
        user.setMusicals(false);
        return user;
    }
}
