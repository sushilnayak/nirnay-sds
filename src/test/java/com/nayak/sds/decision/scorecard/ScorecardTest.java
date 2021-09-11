package com.nayak.sds.decision.scorecard;

import com.nayak.model.Address;
import com.nayak.model.Gender;
import com.nayak.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScorecardTest {
    private final Person person = new Person("Sushil", 35, Gender.MALE, Address.builder().addressLine1("a").addressLine2("b").build());


    @Test
    @DisplayName("Scorecard with Number evaluation falling in otherwise condition")
    void segmentatationNumberCaseWithOtherwiseTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase(Person::getAge)
                .when(12).then(p -> p.withAge(99))
                .when(13).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(77))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(77).address(person.getAddress()).build());
    }

    @Test
    void segmentatationNumberCaseWithTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase(Person::getAge)
                .when(35).then(p -> p.withAddress(person.getAddress().withAddressLine1("R 35, First Floor")))
                .when(13).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(77))
                .build().get();

        Person expectedPerson = Person.builder().name(person.getName()).gender(person.getGender()).age(person.getAge()).address(person.getAddress().withAddressLine1("R 35, First Floor")).build();
        assertThat(build).isEqualTo(expectedPerson);
    }

    @Test
    @DisplayName("Scorecard with String evaluation falling in otherwise condition")
    void segmentatationStringCaseWithOtherwiseTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase(Person::getName)
                .when("xxxx").then(p -> p.withAge(99))
                .when("xxxx").then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(77))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(77).address(person.getAddress()).build());
    }

    @Test
    @DisplayName("Scorecard with String Case evaluation and matching multiple when statements")
    void basicStringTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase(Person::getName)
                .when("Sushil").then(p -> p.withName("dummy"))
                .when("Sushil").then(p -> p.withAge(77))
                .otherwise(p -> p.withName("Lihsus").withAge(99))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name("dummy").age(77).gender(person.getGender()).address(person.getAddress()).build());
    }

    @Test
    @DisplayName("All true WHEN THEN statement are executed sequentially in order of occurance")
    void basicEnumTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase(Person::getGender)
                .when(Gender.MALE).then(p -> p.withAge(77))
                .when(Gender.MALE).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(99))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(88).address(person.getAddress()).build());
    }

    @Test
    void basicEnumOtherwiseTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase(Person::getGender)
                .when(Gender.FEMALE).then(p -> p.withAge(77))
                .otherwise(p -> p.withAge(99))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(99).address(person.getAddress()).build());
    }

    @Test
    @DisplayName("All true WHEN THEN statement are executed sequentially in order of occurance")
    void basicBooleanTest() {

        Person build = Scorecard.using(person).name("Scorecard Dummy")
                .withCase((Person x) -> x.getAge() == 12)
                .when(true).then(p -> p.withAge(77))
                .when(false).then(p -> p.withAge(88))
                .otherwise(p -> p.withAge(99))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).address(person.getAddress()).age(88).build());
    }

    @Test
    @DisplayName("Scorecard w/ case with no condition and condition in when statement")
    void basicCaseWithNoWhenStatement() {

        Person build = Scorecard.using(person).name("Scorecard w/ case with no condition and condition in when statement")
                .withCase()
                .when(p -> p.getName().equals("Sushil")).then(p -> p.withAge(77))
                .when(p -> p.getAge() == 77).then(p -> p.withAddress(person.getAddress().withAddressLine1("abc")))
                .otherwise(p -> p.withAddress(person.getAddress().withAddressLine2("bcd")))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).age(77).gender(person.getGender()).address(person.getAddress().withAddressLine1("abc")).build());
    }
}