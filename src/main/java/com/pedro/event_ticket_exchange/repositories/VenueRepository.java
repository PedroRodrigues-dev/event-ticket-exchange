package com.pedro.event_ticket_exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro.event_ticket_exchange.entities.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
