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

import com.pedro.event_ticket_exchange.entities.Category;
import com.pedro.event_ticket_exchange.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(summary = "Get all categories", description = "Retrieve a paginated list of all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the categories"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    public Page<Category> getAllCategories(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(summary = "Get category by ID", description = "Retrieve a specific category by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the category"),
            @ApiResponse(responseCode = "404", description = "Category not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @Parameter(description = "ID of the category to be retrieved", required = true) @PathVariable("id") Integer id) {

        Optional<Category> category = service.getById(id);

        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
