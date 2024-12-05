package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.Category;
import com.pedro.event_ticket_exchange.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Page<Category> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Category> getById(Integer categoryId) {
        return repository.findById(categoryId);
    }
}