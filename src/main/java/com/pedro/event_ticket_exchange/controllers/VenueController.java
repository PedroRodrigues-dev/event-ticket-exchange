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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    @Autowired
    private VenueService service;

    @Operation(summary = "Get all venues", description = "Retrieve a paginated list of all venues.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the venues list"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    public Page<Venue> getAllVenues(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(summary = "Get venue by ID", description = "Retrieve a specific venue by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the venue"),
            @ApiResponse(responseCode = "404", description = "Venue not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(
            @Parameter(description = "ID of the venue to be retrieved", required = true) @PathVariable("id") Integer id) {

        Optional<Venue> venue = service.getById(id);

        return venue.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
