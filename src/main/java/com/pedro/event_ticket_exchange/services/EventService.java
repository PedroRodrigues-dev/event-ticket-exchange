package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.Event;
import com.pedro.event_ticket_exchange.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Page<Event> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Event> getById(Integer EventId) {
        return repository.findById(EventId);
    }
}