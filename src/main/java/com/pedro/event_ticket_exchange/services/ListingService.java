package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.Listing;
import com.pedro.event_ticket_exchange.repositories.ListingRepository;

@Service
public class ListingService {

    @Autowired
    private ListingRepository repository;

    public Page<Listing> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Listing> getById(Integer ListingId) {
        return repository.findById(ListingId);
    }
}