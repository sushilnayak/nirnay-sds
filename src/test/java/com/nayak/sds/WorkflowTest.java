package com.nayak.sds;

import org.junit.jupiter.api.Test;

class WorkflowTest {

    @Test
    void workflowShouldExecuteSuccessfully() {
        Workflow.name("sample workflow").withData("test data").build();
    }

}