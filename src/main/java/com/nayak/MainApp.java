package com.nayak;

import com.nayak.sds.Workflow;
import lombok.*;

import java.util.List;
import java.util.function.Function;

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


        Person person = new Person("Sushil", 35);

        descision(person);
    }

    public static void descision(Person person) {

        Workflow<Person> workflow = Workflow
                .supplyData(() -> person)
                .name("Main Workflow")
                .initialization(p -> p.withAge(30))
                .decision(p -> Segmentation.using(p)
                        .withBooleanCase(x -> x.getAge() == 12)
                        .when(true).then(w -> postBureau(preBureau(w).build()).build())
                        .when(true).then(w -> Procedure.builder(w)
                                .compose(x -> preBureau(x).build()
                                ).build())
                        .build()
                );

        Summer<Integer> integerSummer = Summer.start(0)
                .then1(1)
                .then2(2);

        Integer apply = integerSummer.apply(9);

        System.out.println(apply);

//        Example<Person> example = Example.builder()
//                .evaluate(person)
//                .withCase()
//                .when().then()
//                .when().then()
//                .when().then()
//                .build();

//                .decision(p -> Segmentation.builder(p)
//                        .withBooleanCase(x -> x.getAge() == 12)
//                        .when(true).then(preBureau(p))
//                        .when(false).then(postBureau(p))
//                        .build());
//                .segementation(Segmentation.condition().trueCondition(procedure1, procedure2, procedure3).falseCondition().build())
//                .segementation(p -> new AgeGt10SubPopulation())
//                .segmentation(p -> new AgeGt10SubPopulation());


        Person build = workflow.build();

        System.out.println(build);
    }

    public static Workflow<Person> preBureau(Person person) {
        return null;
//        return Workflow.supplyData(() -> person)
//                .segmentation(p -> Segmentation.builder(p).name("Address Segmentation")
//                        .withStringCase(p -> p.getAge == 12)
//                        .when(x -> x > 12 && x < 11).then(procedure1, procedure2, procedure3)
//                        .when(x -> x.age == 12).then(procedure1, procedure2, procedure3)
//                        .when(x -> x.name.startsWith("s")).then(procedure1, procedure2, procedure3)
//                        .otherwise(procedire5)
//                        .build()
//                ).segmentation(p -> Segmentation.builder(p).name("Age Segmentation")
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
