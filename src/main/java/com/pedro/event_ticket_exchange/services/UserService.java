package com.pedro.event_ticket_exchange.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedro.event_ticket_exchange.entities.User;
import com.pedro.event_ticket_exchange.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<User> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<User> getById(Integer UserId) {
        return repository.findById(UserId);
    }
}