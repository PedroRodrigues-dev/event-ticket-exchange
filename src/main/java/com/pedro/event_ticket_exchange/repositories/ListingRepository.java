package com.pedro.event_ticket_exchange.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedro.event_ticket_exchange.entities.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {
    @Query("""
            SELECT l.id
            FROM Listing l
            JOIN l.event e
            WHERE e.date.calendarDate <= :contextDate
            AND (:categoryId IS NULL OR e.category.id = :categoryId)
            AND (:eventCity IS NULL OR e.venue.city = :eventCity)
            AND l.numberOfTickets > (SELECT COALESCE(SUM(s.quantitySold), 0) FROM Sale s
            WHERE s.listing.id = l.id)
            ORDER BY e.date DESC
            """)
    List<Long> findListingsToPromotion(
            @Param("contextDate") Date contextDate,
            @Param("categoryId") Long categoryId,
            @Param("eventCity") String eventCity,
            Pageable pageable);
}
