package com.pedro.event_ticket_exchange.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pedro.event_ticket_exchange.entities.Date;
import com.pedro.event_ticket_exchange.entities.Event;
import com.pedro.event_ticket_exchange.entities.Listing;
import com.pedro.event_ticket_exchange.entities.User;
import com.pedro.event_ticket_exchange.services.ListingService;

@SpringBootTest
class ListingControllerTest {

    @Autowired
    private ListingController listingController;

    @MockitoBean
    private ListingService listingService;

    @Test
    void testGetAllListingsPaged() {
        List<Listing> mockListings = Arrays.asList(
                createListing(1, 10, BigDecimal.valueOf(50.00), BigDecimal.valueOf(500.00)),
                createListing(2, 5, BigDecimal.valueOf(75.00), BigDecimal.valueOf(375.00)));

        Page<Listing> mockPage = new PageImpl<>(mockListings, PageRequest.of(0, 10), mockListings.size());

        when(listingService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Listing> result = listingController.getAllListings(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals(10, result.getContent().get(0).getNumberOfTickets());
        assertEquals(BigDecimal.valueOf(500.00), result.getContent().get(0).getTotalPrice());
        assertEquals(5, result.getContent().get(1).getNumberOfTickets());
        assertEquals(BigDecimal.valueOf(375.00), result.getContent().get(1).getTotalPrice());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetListingById_ListingExists() {
        Listing mockListing = createListing(1, 10, BigDecimal.valueOf(50.00), BigDecimal.valueOf(500.00));
        when(listingService.getById(1)).thenReturn(Optional.of(mockListing));

        ResponseEntity<Listing> response = listingController.getListingById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10, response.getBody().getNumberOfTickets());
        assertEquals(BigDecimal.valueOf(500.00), response.getBody().getTotalPrice());
    }

    @Test
    void testGetListingById_ListingNotFound() {
        when(listingService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<Listing> response = listingController.getListingById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Listing createListing(Integer id, Integer numberOfTickets, BigDecimal pricePerTicket,
            BigDecimal totalPrice) {
        Listing listing = new Listing();
        listing.setListingId(id);
        listing.setNumberOfTickets(numberOfTickets);
        listing.setPricePerTicket(pricePerTicket);
        listing.setTotalPrice(totalPrice);
        listing.setListingTimestamp(LocalDateTime.now());

        User seller = new User();
        seller.setUserId(1);
        listing.setSeller(seller);

        Event event = new Event();
        event.setEventId(1);
        listing.setEvent(event);

        Date date = new Date();
        date.setId(1);
        listing.setDate(date);

        return listing;
    }
}