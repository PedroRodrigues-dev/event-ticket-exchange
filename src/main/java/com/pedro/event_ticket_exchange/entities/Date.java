package com.pedro.event_ticket_exchange.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Dates")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "calendar_date", nullable = false)
    private java.sql.Date calendarDate;

    @Column(name = "day", nullable = false)
    private String day;

    @Column(name = "week", nullable = false)
    private Integer week;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "quarter", nullable = false)
    private Integer quarter;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "holiday_flag")
    private Boolean holidayFlag = Boolean.FALSE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(java.sql.Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getHolidayFlag() {
        return holidayFlag;
    }

    public void setHolidayFlag(Boolean holidayFlag) {
        this.holidayFlag = holidayFlag;
    }
}
