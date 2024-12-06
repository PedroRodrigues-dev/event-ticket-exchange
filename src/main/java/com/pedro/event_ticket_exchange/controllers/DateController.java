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

import com.pedro.event_ticket_exchange.entities.Date;
import com.pedro.event_ticket_exchange.services.DateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/dates")
public class DateController {

    @Autowired
    private DateService service;

    @Operation(summary = "Get all dates", description = "Retrieve a paginated list of all dates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the dates"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    public Page<Date> getAllDates(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(summary = "Get date by ID", description = "Retrieve a specific date by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the date"),
            @ApiResponse(responseCode = "404", description = "Date not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Date> getDateById(
            @Parameter(description = "ID of the date to be retrieved", required = true) @PathVariable("id") Integer id) {

        Optional<Date> date = service.getById(id);

        return date.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
