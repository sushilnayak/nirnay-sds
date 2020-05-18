package com.nayak;

import java.util.function.Function;
import java.util.function.Predicate;

public class Segmentation {
    Person result;


    private Segmentation(Person result) {
        this.result = result;
    }

    public static SegmentationUsing using(Person person) {
        return new SegmentationUsing(person);
    }


    static class SegmentationBuild {
        Person result;

        public SegmentationBuild(Person result) {
            this.result = result;
        }

        public Person build() {
            return result;
        }

    }


    static class SegmentationThen {
        Person result;

        public SegmentationThen(Person result) {
            this.result = result;
        }

        public SegmentationBuild thenFinally(Function<Person, Person> procedures) {
            return new SegmentationBuild(result);
        }


        public SegmentationWhen then(Function<Person, Person> procedures) {
            return new SegmentationWhen(result);
        }

    }

    static class SegmentationWhen {
        Person result;

        public SegmentationWhen(Person result) {
            this.result = result;
        }

        public SegmentationThen when(Predicate<Person> predicate) {
            boolean test = predicate.test(result);
            return new SegmentationThen(result);
        }

        public SegmentationThen when(boolean predicate) {
            return new SegmentationThen(result);
        }


        public SegmentationBuild otherwise(Function<Person, Person> procedures) {
            return new SegmentationBuild(result);
        }

    }

    static class SegmentationUsing {
        Person result;

        public SegmentationUsing(Person result) {
            this.result = result;
        }

        public SegmentationWhen withBooleanCase(Predicate<Person> predicate) {
            boolean test = predicate.test(result);
            return new SegmentationWhen(result);
        }

        public SegmentationWhen withStringCase(Function<Person, String> function) {
            String apply = function.apply(result);
            return new SegmentationWhen(result);
        }

        public SegmentationWhen withNumberCase(Function<Person, String> function) {
            String apply = function.apply(result);
            return new SegmentationWhen(result);
        }

        public SegmentationWhen withEnumCase(Function<Person, Enum> function) {
            Enum apply = function.apply(result);
            return new SegmentationWhen(result);
        }
    }

}