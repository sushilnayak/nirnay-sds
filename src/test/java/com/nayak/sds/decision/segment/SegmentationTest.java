package com.nayak.sds.decision.segment;

import com.nayak.model.Address;
import com.nayak.model.Gender;
import com.nayak.model.Person;
import com.nayak.sds.Workflow;
import com.nayak.sds.decision.Procedure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.nayak.sds.decision.common.Range.between;
import static org.assertj.core.api.Assertions.assertThat;

class SegmentationTest {
    private final Person person = new Person("Sushil", 35, Gender.MALE, Address.builder().addressLine1("a").addressLine2("b").build());


    @Test
    void segmentationWithRangeTest() {

        Segmentation<Person> rangeSegmentation = Segmentation.using(person).name("Range Segmentation")
                .withCase(Person::getAge)
                .when(between(1, 10)).then(Procedure.then(x -> x.withAge(1)))
                .when(between(11, 20)).then(Procedure.then(x -> x.withAge(2)))
                .when(between(21, 30)).then(Procedure.then(x -> x.withAge(3)))
                .when(between(31.0, 34.9)).then(Procedure.then(x -> x.withAge(4)))
                .when(between(35, 40)).then(Procedure.then(x -> x.withAge(5)))
                .build();

        System.out.println(rangeSegmentation.get());

    }

    @Test
    @DisplayName("Segmentation with Number evaluation falling in otherwise condition")
    void segmentatationNumberCaseWithOtherwiseTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase(Person::getAge)
                .when(12).then(Procedure.then((Person x) -> x.withAge(77)))
                .when(13).thenProcedure(Procedure.then((Person x) -> x.withAge(88)).andThen(x -> x.withAge(11)))
                .when(14).thenWorkflow(p -> Workflow.name("test").withData(p).process(Procedure.then(x -> x.withAge(99))).build())
                .otherwise(p -> p.withAge(77))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(77).address(person.getAddress()).build());
    }

    @Test
    void segmentatationNumberCaseWithTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase(Person::getAge)
                .when(35).then(Procedure.then((Person x) -> x.withAddress(person.getAddress().withAddressLine1("R 35, First Floor"))))
                .when(13).then(Procedure.then((Person x) -> x.withAge(88)))
                .otherwise(Procedure.then((Person x) -> x.withAge(99)))
                .build().get();

        Person expectedPerson = Person.builder().name(person.getName()).gender(person.getGender()).age(person.getAge()).address(person.getAddress().withAddressLine1("R 35, First Floor")).build();
        assertThat(build).isEqualTo(expectedPerson);
    }

    @Test
    @DisplayName("Segmentation with String evaluation falling in otherwise condition")
    void segmentatationStringCaseWithOtherwiseTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase(Person::getName)
                .when("xxxx").then(Procedure.then(p -> p.withAge(99)))
                .when("xxxx").then(Procedure.then(p -> p.withAge(88)))
                .otherwise(p -> p.withAge(77))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(77).address(person.getAddress()).build());
    }

    @Test
    @DisplayName("Segmentation with String Case evaluation and matching multiple when statements")
    void basicStringTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase(Person::getName)
                .when("Sushil").then(Procedure.then(p -> p.withName("dummy")))
                .when("Sushil").then(Procedure.then(p -> p.withAge(77)))
                .otherwise(Procedure.then((Person p) -> p.withName("Lihsus")).andThen(p -> p.withAge(99)))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name("dummy").age(77).gender(person.getGender()).address(person.getAddress()).build());
    }

    @Test
    @DisplayName("All true WHEN THEN statement are executed sequentially in order of occurance")
    void basicEnumTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase(Person::getGender)
                .when(Gender.MALE).then(Procedure.then(p -> p.withAge(77)))
                .when(Gender.MALE).then(Procedure.then(p -> p.withAge(88)))
                .otherwise(Procedure.then(p -> p.withAge(99)))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(88).address(person.getAddress()).build());
    }

    @Test
    void basicEnumOtherwiseTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase(Person::getGender)
                .when(Gender.FEMALE).then(Procedure.then(p -> p.withAge(77)))
                .otherwise(Procedure.then(p -> p.withAge(99)))
                .build()
                .get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).age(99).address(person.getAddress()).build());
    }

    @Test
    @DisplayName("All true WHEN THEN statement are executed sequentially in order of occurance")
    void basicBooleanTest() {

        Person build = Segmentation.using(person).name("Segmentation Dummy")
                .withCase((Predicate<Person>) x -> x.getAge() == 12)
                .when(true).then((Function<Person, Workflow<Person>>) x -> Workflow.name("test").withData(x).build())
                .when(false).then(Procedure.then(p -> p.withAge(88)))
                .otherwise(Procedure.then(p -> p.withAge(99)))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).gender(person.getGender()).address(person.getAddress()).age(88).build());
    }

    @Test
    @DisplayName("Segmentation w/ case with no condition and condition in when statement")
    void basicCaseWithNoWhenStatement() {

        Person build = Segmentation.using(person).name("Segmentation w/ case with no condition and condition in when statement")
                .withCase()
                .when((Predicate<Person>) p -> p.getName().equals("Sushil")).then(Procedure.then(p -> p.withAge(77)))
                .when((Predicate<Person>) p -> p.getAge() == 77).then(Procedure.then(p -> p.withAddress(person.getAddress().withAddressLine1("abc"))))
                .otherwise(Procedure.then(p -> p.withAddress(person.getAddress().withAddressLine2("bcd"))))
                .build().get();

        assertThat(build).isEqualTo(Person.builder().name(person.getName()).age(77).gender(person.getGender()).address(person.getAddress().withAddressLine1("abc")).build());
    }
}