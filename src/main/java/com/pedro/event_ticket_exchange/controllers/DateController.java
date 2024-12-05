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

@RestController
@RequestMapping("/api/v1/dates")
public class DateController {

    @Autowired
    private DateService service;

    @GetMapping
    public Page<Date> getAllDates(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Date> getDateById(@PathVariable("id") Integer id) {
        Optional<Date> date = service.getById(id);

        return date.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
