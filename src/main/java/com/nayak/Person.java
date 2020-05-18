package com.nayak;

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
}