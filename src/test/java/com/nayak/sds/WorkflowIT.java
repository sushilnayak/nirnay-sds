package com.nayak.sds;

import com.nayak.model.Address;
import com.nayak.model.Gender;
import com.nayak.model.Person;
import com.nayak.sds.decision.Procedure;
import com.nayak.sds.decision.segment.Segmentation;
import org.junit.jupiter.api.Test;

import static com.nayak.constants.Segmentations.BASIC_SEGMENTATION;
import static com.nayak.constants.Segmentations.PERSON_SEGMENTATION_FUNCTION;
import static org.assertj.core.api.Assertions.assertThat;

class WorkflowIT {
    Person person = new Person("Sushil", 35, Gender.MALE, Address.builder().addressLine1("xx").build());

    @Test
    void basicFlow() {
        Person descision = decision(person);

        assertThat(descision).isEqualTo(Person.builder()
                .name("SUSHILS")
                .age(11)
                .gender(Gender.MALE)
                .address(Address.builder().addressLine1("xx").country("India").build())
                .build());
    }


    public Person decision(Person person) {


        Workflow<Person> workflow = Workflow.<Person>name("Sushil Workflows")
                .withData(person)
                .initialize(p -> p.withAge(30))
                .log()
                .segmentation(PERSON_SEGMENTATION_FUNCTION)
                .log()
                .segmentation(BASIC_SEGMENTATION)
                .log()
                .build();

        return workflow.get();
    }

    public Workflow<Person> preBureau(Person person) {
        return Workflow.name("Pre-Bureau")
                .withData(person)
                .segmentation(pers -> Segmentation.using(pers).name("Test Segmentation")
                        .withCase((Person x) -> x.getGender() == Gender.MALE)
                        .when(false)
                        .thenProcedure(Procedure.then(p -> p.withAddress(p.getAddress().withAddressLine1("qqqq"))))
                        .build())
                .build();
    }

    public Workflow<Person> postBureau(Person person) {

        return Workflow.name("Test Workflow").withData(person).build();

    }
}