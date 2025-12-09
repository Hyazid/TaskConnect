package com.example.ServiceApp.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    @Override
    public String toString() {
        return String.format("%s, %s, %s %s", street, city, state, zipCode);
    }
}
