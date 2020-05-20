package com.nayak.sds.decision.segment.then;

import com.nayak.sds.decision.segment.when.SegmentationWhenBoolean;

import java.util.function.Function;

public class SegmentationBooleanThen<T> extends SegmentationThen<T> {
    boolean caseCondition;
    boolean condition;
    T result;

    public SegmentationBooleanThen(boolean caseCondition, boolean condition, T result) {
        this.caseCondition = caseCondition;
        this.condition = condition;
        this.result = result;
    }

    public SegmentationWhenBoolean<T> then(Function<T, T> procedures) {
        if (this.condition) {
            return new SegmentationWhenBoolean<T>(caseCondition, this.condition,  procedures.apply(result));
        } else return new SegmentationWhenBoolean<T>(caseCondition, this.condition , result);
    }
}