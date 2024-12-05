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
import com.pedro.event_ticket_exchange.entities.Sale;
import com.pedro.event_ticket_exchange.entities.User;
import com.pedro.event_ticket_exchange.services.SaleService;

@SpringBootTest
class SaleControllerTest {

    @Autowired
    private SaleController saleController;

    @MockitoBean
    private SaleService saleService;

    @Test
    void testGetAllSalesPaged() {
        List<Sale> mockSales = Arrays.asList(
                createSale(1, 5, BigDecimal.valueOf(100.00), BigDecimal.valueOf(10.00)),
                createSale(2, 2, BigDecimal.valueOf(50.00), BigDecimal.valueOf(5.00)));

        Page<Sale> mockPage = new PageImpl<>(mockSales, PageRequest.of(0, 10), mockSales.size());

        when(saleService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Sale> result = saleController.getAllSales(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals(5, result.getContent().get(0).getQuantitySold());
        assertEquals(BigDecimal.valueOf(100.00), result.getContent().get(0).getPricePaid());
        assertEquals(2, result.getContent().get(1).getQuantitySold());
        assertEquals(BigDecimal.valueOf(50.00), result.getContent().get(1).getPricePaid());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetSaleById_SaleExists() {
        Sale mockSale = createSale(1, 3, BigDecimal.valueOf(75.00), BigDecimal.valueOf(7.50));
        when(saleService.getById(1)).thenReturn(Optional.of(mockSale));

        ResponseEntity<Sale> response = saleController.getSaleById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().getQuantitySold());
        assertEquals(BigDecimal.valueOf(75.00), response.getBody().getPricePaid());
    }

    @Test
    void testGetSaleById_SaleNotFound() {
        when(saleService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<Sale> response = saleController.getSaleById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Sale createSale(Integer id, Integer quantitySold, BigDecimal pricePaid, BigDecimal commissionAmount) {
        Sale sale = new Sale();
        sale.setSaleId(id);
        sale.setQuantitySold(quantitySold);
        sale.setPricePaid(pricePaid);
        sale.setCommissionAmount(commissionAmount);
        sale.setSaleTimestamp(LocalDateTime.now());

        Listing listing = new Listing();
        listing.setListingId(1);
        sale.setListing(listing);

        User seller = new User();
        seller.setUserId(1);
        sale.setSeller(seller);

        User buyer = new User();
        buyer.setUserId(2);
        sale.setBuyer(buyer);

        Event event = new Event();
        event.setEventId(1);
        sale.setEvent(event);

        Date date = new Date();
        date.setId(1);
        sale.setDate(date);

        return sale;
    }
}
