package com.pedro.event_ticket_exchange.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import com.pedro.event_ticket_exchange.services.DateService;

@SpringBootTest
class DateControllerTest {

    @Autowired
    private DateController dateController;

    @MockitoBean
    private DateService dateService;

    @Test
    void testGetAllDatesPaged() {
        List<Date> mockDates = Arrays.asList(
                createDate(1, java.sql.Date.valueOf("2024-01-01"), "WE", 1, "JUL", 1, 2024, false),
                createDate(2, java.sql.Date.valueOf("2024-01-02"), "TU", 1, "JUL", 1, 2024, true));

        Page<Date> mockPage = new PageImpl<>(mockDates, PageRequest.of(0, 10), mockDates.size());

        when(dateService.getAll(any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Date> result = dateController.getAllDates(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals(2024, result.getContent().get(0).getYear());
        assertEquals(true, result.getContent().get(1).getHolidayFlag());

        assertEquals(10, result.getSize());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testGetDateById_DateExists() {
        Date mockDate = createDate(1, java.sql.Date.valueOf("2024-01-01"), "WE", 1, "JUL", 1, 2024, false);
        when(dateService.getById(1)).thenReturn(Optional.of(mockDate));

        ResponseEntity<Date> response = dateController.getDateById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2024, response.getBody().getYear());
        assertEquals(false, response.getBody().getHolidayFlag());
    }

    @Test
    void testGetDateById_DateNotFound() {
        when(dateService.getById(1)).thenReturn(Optional.empty());

        ResponseEntity<Date> response = dateController.getDateById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Date createDate(Integer id, java.sql.Date calendarDate, String day, Integer week, String month,
            Integer quarter, Integer year, Boolean holidayFlag) {
        Date date = new Date();
        date.setId(id);
        date.setCalendarDate(calendarDate);
        date.setDay(day);
        date.setWeek(week);
        date.setMonth(month);
        date.setQuarter(quarter);
        date.setYear(year);
        date.setHolidayFlag(holidayFlag);

        return date;
    }
}
