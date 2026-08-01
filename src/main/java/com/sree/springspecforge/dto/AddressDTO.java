package com.sree.springspecforge.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Address details exposed in API responses.
 * Prevents bidirectional entity graphs from leaking into the JSON payload.
 */
public class AddressDTO {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Default constructor.
     */
    public AddressDTO() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id      address identifier
     * @param street  street line
     * @param city    city
     * @param state   state / province
     * @param zipCode postal / ZIP code
     * @param country country
     */
    public AddressDTO(Long id, String street, String city, String state, String zipCode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(street, that.street)
                && Objects.equals(city, that.city)
                && Objects.equals(state, that.state)
                && Objects.equals(zipCode, that.zipCode)
                && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, state, zipCode, country);
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
