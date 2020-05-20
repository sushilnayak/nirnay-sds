package com.nayak.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Data
@Builder
@NoArgsConstructor
public class Person {
    @With
    private String name;
    @With
    private int age;
    @With
    private Gender gender;

    @With
    private Address address;
}