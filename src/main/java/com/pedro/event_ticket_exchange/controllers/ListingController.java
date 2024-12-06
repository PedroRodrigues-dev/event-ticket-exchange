package com.pedro.event_ticket_exchange.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pedro.event_ticket_exchange.entities.Listing;
import com.pedro.event_ticket_exchange.services.ListingService;

@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {

    @Autowired
    private ListingService service;

    @GetMapping
    public Page<Listing> getAllListings(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable("id") Integer id) {
        Optional<Listing> listing = service.getById(id);

        return listing.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/promotions")
    public List<Long> getListingsToPromotion(
            @RequestParam(value = "contextDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date contextDate,

            @RequestParam(value = "categoryId", required = false) Long categoryId,

            @RequestParam(value = "eventCity", required = false) String eventCity) {

        if (contextDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "contextDate is required");
        }

        return service.getListingsToPromotion(contextDate, categoryId, eventCity);
    }

}
