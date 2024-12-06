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

import com.pedro.event_ticket_exchange.entities.Event;
import com.pedro.event_ticket_exchange.services.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService service;

    @Operation(summary = "Get all events", description = "Retrieve a paginated list of all events.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the events"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    public Page<Event> getAllEvents(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(summary = "Get event by ID", description = "Retrieve a specific event by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the event"),
            @ApiResponse(responseCode = "404", description = "Event not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(
            @Parameter(description = "ID of the event to be retrieved", required = true) @PathVariable("id") Integer id) {

        Optional<Event> event = service.getById(id);

        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
