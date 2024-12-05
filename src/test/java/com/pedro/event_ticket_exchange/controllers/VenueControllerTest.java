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

import com.pedro.event_ticket_exchange.entities.Venue;
import com.pedro.event_ticket_exchange.services.VenueService;

@SpringBootTest
class VenueControllerTest {

    @Autowired
    private VenueController venueController;

    @MockitoBean
    private VenueService venueService;

    @Test
    void testGetAllVenuesPaged() {
        List<Venue> mockVenues = Arrays.asList(
                createVenue(1, "Stadium A", "City1", "State1", 50000),
                createVenue(2, "Arena B", "City2", "State2", 20000));

        Page<Venue> mockPage = new PageImpl<>(mockVenues, PageRequest.of(0, 10), mockVenues.size());

        when(venueService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Venue> result = venueController.getAllVenues(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals("Stadium A", result.getContent().get(0).getVenueName());
        assertEquals(50000, result.getContent().get(0).getSeatingCapacity());
        assertEquals("Arena B", result.getContent().get(1).getVenueName());
        assertEquals(20000, result.getContent().get(1).getSeatingCapacity());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetVenueById_VenueExists() {
        Venue mockVenue = createVenue(1, "Stadium A", "City1", "State1", 50000);
        when(venueService.getById(1)).thenReturn(Optional.of(mockVenue));

        ResponseEntity<Venue> response = venueController.getVenueById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Stadium A", response.getBody().getVenueName());
    }

    @Test
    void testGetVenueById_VenueNotFound() {
        when(venueService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<Venue> response = venueController.getVenueById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Venue createVenue(Integer id, String venueName, String city, String state, Integer seatingCapacity) {
        Venue venue = new Venue();
        venue.setVenueId(id);
        venue.setVenueName(venueName);
        venue.setCity(city);
        venue.setState(state);
        venue.setSeatingCapacity(seatingCapacity);
        return venue;
    }
}
