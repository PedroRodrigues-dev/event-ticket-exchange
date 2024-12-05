package com.pedro.event_ticket_exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro.event_ticket_exchange.entities.Date;

@Repository
public interface DateRepository extends JpaRepository<Date, Integer> {

}
