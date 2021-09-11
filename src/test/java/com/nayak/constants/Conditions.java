package com.nayak.constants;

import com.nayak.model.Person;

import java.util.function.Predicate;

public final class Conditions {

    private Conditions() {
    }

    public static final Predicate<Person> AGE_EQ_12_YEAR = person -> person.getAge() == 12;

}
