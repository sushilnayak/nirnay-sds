package com.nayak.sds;

public interface WorkflowContextHolder {
    ThreadLocal<StringBuilder> workflowPath = ThreadLocal.withInitial(StringBuilder::new);
}
