package com.nayak.model;


import lombok.*;

@AllArgsConstructor
@ToString
@Data
@Builder
@NoArgsConstructor
public class Address {
    @With
    private String addressLine1;
    @With
    private String addressLine2;
    @With
    private String addressLine3;
    @With
    private String country;
}
