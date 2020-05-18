package com.nayak;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;

public class Segmentation {
    Person result;


    private Segmentation(Person result) {
        this.result = result;
    }

    public static Segmentation using(Person person) {
        return new Segmentation(person);
    }

    public Segmentation withBooleanCase(Predicate<Person> predicate) {
        boolean test = predicate.test(result);
        return new Segmentation(result);
    }

    public Segmentation withStringCase(Function<Person, String> function) {
        String apply = function.apply(result);
        return new Segmentation(result);
    }

    public Segmentation withNumberCase(Function<Person, String> function) {
        String apply = function.apply(result);
        return new Segmentation(result);
    }

    public Segmentation withEnumCase(Function<Person, Enum> function) {
        Enum apply = function.apply(result);
        return new Segmentation(result);
    }

    public Segmentation when(Predicate<Person> predicate) {
        boolean test = predicate.test(result);
        return new Segmentation(result);
    }

    public Segmentation when(boolean predicate) {
        return new Segmentation(result);
    }

    public Segmentation then(Function<Person, Person> procedures) {

        return new Segmentation(result);
    }

    public Person build() {
        return result;
    }

}