package com.pedro.event_ticket_exchange.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.event_ticket_exchange.entities.Listing;
import com.pedro.event_ticket_exchange.services.ListingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {

        @Autowired
        private ListingService service;

        @Operation(summary = "Get all listings", description = "Retrieve a paginated list of all listings.", parameters = {
                        @Parameter(name = "page", description = "Page number (0-based index)", in = ParameterIn.QUERY, example = "0"),
                        @Parameter(name = "size", description = "Number of items per page", in = ParameterIn.QUERY, example = "10")
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved the listings"),
                        @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
        })
        @GetMapping
        public Page<Listing> getAllListings(@Parameter(hidden = true) Pageable pageable) {
                return service.getAll(pageable);
        }

        @Operation(summary = "Get listing by ID", description = "Retrieve a specific listing by its ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved the listing"),
                        @ApiResponse(responseCode = "404", description = "Listing not found with the given ID")
        })
        @GetMapping("/{id}")
        public ResponseEntity<Listing> getListingById(
                        @Parameter(description = "ID of the listing to be retrieved", required = true) @PathVariable("id") Integer id) {

                Optional<Listing> listing = service.getById(id);

                return listing.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @Operation(summary = "Get listings for promotion", description = "Retrieve listings that are eligible for promotion based on date, category, and event city.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved listings for promotion"),
                        @ApiResponse(responseCode = "400", description = "Invalid or missing 'contextDate' parameter")
        })
        @GetMapping("/promotions")
        public List<Long> getListingsToPromotion(
                        @Parameter(description = "The context date to filter promotions", required = true) @RequestParam(value = "contextDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date contextDate,

                        @Parameter(description = "The ID of the category to filter listings", required = false) @RequestParam(value = "categoryId", required = false) Long categoryId,

                        @Parameter(description = "The city of the event to filter listings", required = false) @RequestParam(value = "eventCity", required = false) String eventCity) {

                return service.getListingsToPromotion(contextDate, categoryId, eventCity);
        }
}
