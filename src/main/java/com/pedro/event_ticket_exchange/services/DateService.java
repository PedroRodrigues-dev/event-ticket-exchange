package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.Date;
import com.pedro.event_ticket_exchange.repositories.DateRepository;

@Service
public class DateService {

    @Autowired
    private DateRepository repository;

    public Page<Date> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Date> getById(Integer DateId) {
        return repository.findById(DateId);
    }
}