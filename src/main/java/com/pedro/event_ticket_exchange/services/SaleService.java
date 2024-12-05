package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.Sale;
import com.pedro.event_ticket_exchange.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Page<Sale> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Sale> getById(Integer SaleId) {
        return repository.findById(SaleId);
    }
}