package com.pedro.event_ticket_exchange.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_state")
    private String addressState;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sports")
    private Boolean sports;

    @Column(name = "theatre")
    private Boolean theatre;

    @Column(name = "concerts")
    private Boolean concerts;

    @Column(name = "jazz")
    private Boolean jazz;

    @Column(name = "classical")
    private Boolean classical;

    @Column(name = "opera")
    private Boolean opera;

    @Column(name = "rock")
    private Boolean rock;

    @Column(name = "vegas")
    private Boolean vegas;

    @Column(name = "broadway")
    private Boolean broadway;

    @Column(name = "musicals")
    private Boolean musicals;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getSports() {
        return sports;
    }

    public void setSports(Boolean sports) {
        this.sports = sports;
    }

    public Boolean getTheatre() {
        return theatre;
    }

    public void setTheatre(Boolean theatre) {
        this.theatre = theatre;
    }

    public Boolean getConcerts() {
        return concerts;
    }

    public void setConcerts(Boolean concerts) {
        this.concerts = concerts;
    }

    public Boolean getJazz() {
        return jazz;
    }

    public void setJazz(Boolean jazz) {
        this.jazz = jazz;
    }

    public Boolean getClassical() {
        return classical;
    }

    public void setClassical(Boolean classical) {
        this.classical = classical;
    }

    public Boolean getOpera() {
        return opera;
    }

    public void setOpera(Boolean opera) {
        this.opera = opera;
    }

    public Boolean getRock() {
        return rock;
    }

    public void setRock(Boolean rock) {
        this.rock = rock;
    }

    public Boolean getVegas() {
        return vegas;
    }

    public void setVegas(Boolean vegas) {
        this.vegas = vegas;
    }

    public Boolean getBroadway() {
        return broadway;
    }

    public void setBroadway(Boolean broadway) {
        this.broadway = broadway;
    }

    public Boolean getMusicals() {
        return musicals;
    }

    public void setMusicals(Boolean musicals) {
        this.musicals = musicals;
    }
}
