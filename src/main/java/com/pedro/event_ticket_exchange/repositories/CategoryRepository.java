package com.pedro.event_ticket_exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro.event_ticket_exchange.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
