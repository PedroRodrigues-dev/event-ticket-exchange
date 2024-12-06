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

import com.pedro.event_ticket_exchange.entities.Sale;
import com.pedro.event_ticket_exchange.services.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

        @Autowired
        private SaleService service;

        @Operation(summary = "Get all sales", description = "Retrieve a paginated list of all sales.", parameters = {
                        @Parameter(name = "page", description = "Page number (0-based index)", in = ParameterIn.QUERY, example = "0"),
                        @Parameter(name = "size", description = "Number of items per page", in = ParameterIn.QUERY, example = "10")
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved the sales list"),
                        @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
        })
        @GetMapping
        public Page<Sale> getAllSales(@Parameter(hidden = true) Pageable pageable) {
                return service.getAll(pageable);
        }

        @Operation(summary = "Get sale by ID", description = "Retrieve a specific sale by its ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved the sale"),
                        @ApiResponse(responseCode = "404", description = "Sale not found with the given ID")
        })
        @GetMapping("/{id}")
        public ResponseEntity<Sale> getSaleById(
                        @Parameter(description = "ID of the sale to be retrieved", required = true) @PathVariable("id") Integer id) {

                Optional<Sale> sale = service.getById(id);

                return sale.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
        }
}
