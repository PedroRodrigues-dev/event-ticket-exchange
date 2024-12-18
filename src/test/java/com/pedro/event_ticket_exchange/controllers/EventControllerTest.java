package com.pedro.event_ticket_exchange.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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

import com.pedro.event_ticket_exchange.entities.Category;
import com.pedro.event_ticket_exchange.entities.Date;
import com.pedro.event_ticket_exchange.entities.Event;
import com.pedro.event_ticket_exchange.entities.Venue;
import com.pedro.event_ticket_exchange.services.EventService;

@SpringBootTest
class EventControllerTest {

    @Autowired
    private EventController eventController;

    @MockitoBean
    private EventService eventService;

    @Test
    void testGetAllEventsPaged() {
        List<Event> mockEvents = Arrays.asList(
                createEvent(1, "Concert A", LocalDateTime.of(2024, 6, 12, 20, 0, 0,
                        0)),
                createEvent(2, "Concert B", LocalDateTime.of(2024, 7, 12, 23, 0, 0, 0)));

        Page<Event> mockPage = new PageImpl<>(mockEvents, PageRequest.of(0, 10), mockEvents.size());

        when(eventService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Event> result = eventController.getAllEvents(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals("Concert A", result.getContent().get(0).getEventName());
        assertEquals(LocalDateTime.of(2024, 6, 12, 20, 0, 0, 0), result.getContent().get(0).getEventStartTime());
        assertEquals("Concert B", result.getContent().get(1).getEventName());
        assertEquals(LocalDateTime.of(2024, 7, 12, 23, 0, 0, 0), result.getContent().get(1).getEventStartTime());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetEventById_EventExists() {
        Event mockEvent = createEvent(1, "Concert A", LocalDateTime.of(2024, 6, 12, 20, 0, 0, 0));
        ;
        when(eventService.getById(1)).thenReturn(Optional.of(mockEvent));

        ResponseEntity<Event> response = eventController.getEventById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Event body = Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new AssertionError("Response body should not be null"));
        assertEquals("Concert A", body.getEventName());
        assertEquals(LocalDateTime.of(2024, 6, 12, 20, 0, 0, 0), body.getEventStartTime());
    }

    @Test
    void testGetEventById_EventNotFound() {
        when(eventService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<Event> response = eventController.getEventById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Event createEvent(Integer id, String eventName, LocalDateTime eventStartTime) {
        Event event = new Event();
        event.setEventId(id);
        event.setEventName(eventName);
        event.setEventStartTime(eventStartTime);

        Venue venue = new Venue();
        venue.setVenueId(1);
        event.setVenue(venue);

        Category category = new Category();
        category.setCategoryId(1);
        event.setCategory(category);

        Date date = new Date();
        date.setId(1);
        event.setDate(date);

        return event;
    }
}
