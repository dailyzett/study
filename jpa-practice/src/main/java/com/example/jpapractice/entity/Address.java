package com.example.jpapractice.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
