package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.Venue;
import com.pedro.event_ticket_exchange.repositories.VenueRepository;

@Service
public class VenueService {

    @Autowired
    private VenueRepository repository;

    public Page<Venue> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Venue> getById(Integer VenueId) {
        return repository.findById(VenueId);
    }
}