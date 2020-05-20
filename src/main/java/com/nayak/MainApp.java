package com.nayak;

import com.nayak.model.Address;
import com.nayak.model.Gender;
import com.nayak.model.Person;
import com.nayak.sds.Workflow;
import com.nayak.sds.decision.segment.Segmentation;

public class MainApp {
    public static void main(String[] args) {
//        Workflow

//        Subroutine - concept will be implementated by user in the form of methods/functions which returns a Workflow and takes T as the input

//        Conditional statement for routing to

//       Segmentation
//        - Subpopulation  -> BOolean - true/false
//        - Classset -> Categorial Type
//        - Matrices -> CrossTab ( n * n matix )

//        Scorecard => intial scoring (optional) -> classsets (multiple 1 or more) -> transformation(optional) -> Segmentation() -> Segmentation ->


//        Derived Script  -- can ignore
//        User Function  --

//        Policy Rule Set -> Workflow -> Policy Rule


        Person person = new Person("Sushil", 35, Gender.MALE, Address.builder().addressLine1("xx").build());

        descision(person);
    }

    public static void descision(Person person) {

        Workflow<Person> workflow = Workflow
                .supplyData(() -> person)
                .name("Main Workflow")
                .initialization(p -> p.withAge(30))
                .log()
                .decision(person1 -> Segmentation.using(person1).name("Age Based Segmentation")
                        .withBooleanCase(x -> x.getAge() == 30)
                        .when(true).then(w -> postBureau(preBureau(w).build()).build())
                        .when(false).then(x -> x.withAge(99))
                        .build());

        Person build = workflow.build();

        System.out.println(build);
    }

    public static Workflow<Person> preBureau(Person person) {
        return Workflow.supplyData(() -> person)
                .decision(p -> Segmentation.using(p).name("Test Segmentation")
                        .withBooleanCase(x -> x.getGender() == Gender.MALE).when(true).then(a -> a.withAddress(a.getAddress().withAddressLine1("qqqqqqq"))).build());
//        return Workflow.supplyData(() -> person)
//                .decision(p -> Segmentation.using(p).name("Generic Segmentation")
////                        .name("Address Segmentation")
//                                .withNumberCase(Person::getAge)
//                                .when(number -> number.intValue() > 12 && number.intValue() < 11)
//                                .then(procedure1, procedure2, procedure3)
//                                .when(x -> x.intValue() == 12)
//                                .then(procedure1, procedure2, procedure3)
//                                .otherwise(procedire5)
//                                .build()
//                ).segmentation(p -> Segmentation.using(p).name("Age Segmentation")
//                        .withNumberCase(p -> p.getAge == 12)
//                        .when(x -> x > 12 && x < 11).then(p -> {
//                            preBureau(p);
//                            shovik(p);
//                            sushil(p);
//                        })
//                        .when(17).then(procedure1, procedure2, procedure3)
//                        .when(19).then(procedure1, procedure2, procedure3)
//                        .otherwise(procedire5)
//                        .build()
//                );
    }

    public static Workflow<Person> postBureau(Person person) {
        return Workflow.supplyData(() -> person);
    }


}
