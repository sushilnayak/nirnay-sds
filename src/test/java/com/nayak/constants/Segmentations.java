package com.nayak.constants;

import com.nayak.model.Person;
import com.nayak.sds.Workflow;
import com.nayak.sds.decision.Procedure;
import com.nayak.sds.decision.segment.Segmentation;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.nayak.constants.Conditions.AGE_EQ_12_YEAR;

public final class Segmentations {

    private Segmentations() {
    }


    public static final Function<Person, Segmentation<Person>> PERSON_SEGMENTATION_FUNCTION = person -> Segmentation.using(person).name("Person Segmentation")
            //@formatter:off
            .withCase(AGE_EQ_12_YEAR)
                .when(true)
                    .thenWorkflow( pers-> Workflow.name("av1")
                            .withData(pers)
                            .process(Procedure.then(p-> p.withName("ac") ))
                            .build() )
                .when(false)
            .thenWorkflow(pers-> Workflow.name("av2")
                    .withData(pers)
                    .process(Procedure.then(p-> p.withAge(11) ))
                    .build() )
            .build();
            //@formatter:on

    public static final Function<Person, Segmentation<Person>> BASIC_SEGMENTATION = person -> Segmentation.using(person).name("xxxxxx")
            //@formatter:off
            .withCase()
               .when((Predicate<Person>) x -> x.getAge() == 12 && x.getName().startsWith("x"))
                  .then(Procedure.then(x -> x.withName("LIHSUS") ))
               .when((Predicate<Person>) x -> x.getAge() == 11 && x.getName().startsWith("S"))
                  .then(Procedure.then(x->x.withName("SUSHILS").withAddress(x.getAddress().withCountry("India"))))
               .otherwise(Procedure.then(x -> x.withAddress(x.getAddress().withAddressLine3("oooooooooo"))))
            .build();
             //@formatter:on
}
