package com.nayak.sds.decision.segment;

import com.nayak.model.Address;
import com.nayak.model.Gender;
import com.nayak.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentationTest {
    private Person person = new Person("Sushil", 35, Gender.MALE, Address.builder().addressLine1("a").addressLine2("b").build());


    @Test
    @DisplayName("Segmentation with Number evaluation falling in otherwise condition")
    void segmentatationWithOtherwiseTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withNumberCase(Person::getAge)
                .when(12).then(p -> p.withAge(99))
                .when(13).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(77))
                .build();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(77).build());
    }

    @Test
    @DisplayName("Segmentation with ")
    void basicStringTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withStringCase(Person::getName)
                .when("Sushil").then(p -> p.withName("dummy"))
                .when("Sushil").then(p -> p.withAge(77))
                .otherwise(p -> p.withName("Lihsus").withAge(99))
                .build();

        assertThat(build).isEqualTo(Person.builder().name("dummy").age(77).gender(person.getGender()).build());
    }

    @Test
    @DisplayName("All true WHEN THEN statement are executed sequentially in order of occurance")
    public void basicEnumTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withEnumCase(Person::getGender)
                .when(Gender.MALE).then(p -> p.withAge(77))
                .when(Gender.MALE).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(99))
                .build();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(88).build());
    }

    @Test
    @DisplayName("All true WHEN THEN statement are executed sequentially in order of occurance")
    public void basicBooleanTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withBooleanCase(x -> x.getAge() == 12)
                .when(true).then(p -> p.withAge(77))
                .when(false).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(99))
                .build();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(88).build());
    }
}