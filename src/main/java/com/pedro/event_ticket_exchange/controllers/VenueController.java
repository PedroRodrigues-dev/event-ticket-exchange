package com.pedro.event_ticket_exchange.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.event_ticket_exchange.entities.Venue;
import com.pedro.event_ticket_exchange.services.VenueService;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    @Autowired
    private VenueService service;

    @GetMapping
    public Page<Venue> getAllVenues(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable("id") Integer id) {
        Optional<Venue> venue = service.getById(id);

        return venue.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}