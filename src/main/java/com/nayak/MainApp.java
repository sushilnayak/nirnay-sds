package com.nayak;

import com.nayak.sds.Workflow;
import com.nayak.sds.procedure.Procedure;
import com.nayak.sds.segmentation.Segmentation;
import com.nayak.sds.segmentation.SubPopulation;
import lombok.*;

import java.util.Collections;
import java.util.LinkedList;

public class MainApp {
    public static void main(String[] args) {
//        Workflow

//        Subroutine - concept will be implementated by user in the form of methods/functions which returns a Workflow and takes T as the input

//        Conditional statement for routing to

//       Segmentation
//        - Subpopulation  -> BOolean - true/false
//        - Classset -> Categorial Type
//        - Matrices -> CrossTab ( n * n matix )

//        Scorecard => intial scoring (optional) -> classsets (multiple 1 or more) -> transformation(optional)


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
                .decision(p -> p, Segmentation.condition(ageCondition).condition().condition().build())
                .decision(p -> p, Segmentation.condition(ageCondition).condition().build())
                .segementation(p -> p, Segmentation.condition(booleanCondition)
                        .trueCondition(procedure1, procedure2, procedure3)
                        .falseCondition(falseCondition1)
                        .build())
                .segementation(p -> p, Segmentation.condition(categorialCondition)
                        .firstCondition(procedure1, procedure2, procedure3)
                        .secondCondition(secondProcedure1)
                        .thirdCondition(falseCondition1)
                        .build())
                ;
//                .segementation(Segmentation.condition().trueCondition(procedure1, procedure2, procedure3).falseCondition().build())
//                .segementation(p -> new AgeGt10SubPopulation())
//                .segmentation(p -> new AgeGt10SubPopulation());


        Person build = workflow.build();

        System.out.println(build);
//                .subroutine(s -> Conditional.when(person1 -> person.getAge() == s.getAge() && s.getAge() == 12, preBureau(s)));


//                .subroutine(s -> Conditional.when(p -> p.getAge() == 30, person -> postBureau(person))
//                                            .or(p -> p.getSalary() -> 30000, person -> randomBureau(person))
//                                            .otherwise(person -> preBureau(person)));

//        Workflow( name ) -> Initialization( name ) -> log -> subroutine (name ) -> Decision (postbeaure) ->


    }

    //    public static Workflow<Person> postBureau(Person person) {
//        return Workflow.supplyData(() -> person)
//                .scorecard()
//                .policyRuleSet();
//    }
//
    public static Workflow<Person> preBureau(Person person) {
        return Workflow.supplyData(() -> person);
    }

    //
//    public static Workflow<Person> randomBureau(Person person) {
//        return Workflow.supplyData(() -> person);
//    }
    @AllArgsConstructor
    @ToString
    @Data
    @NoArgsConstructor
    static class Person {
        @With
        private String name;
        @With
        private int age;
    }

    static class AgeGt10SubPopulation implements SubPopulation<Person> {

        @Override
        public String getName() {
            return "Age > 10";
        }

        @Override
        public Boolean apply(Person person) {
            return person.getAge() > 10;
        }

    }
}
