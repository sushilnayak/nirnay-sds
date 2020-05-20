package com.nayak.sds.decision.segment.then;

import com.nayak.sds.decision.segment.when.SegmentationWhenNumber;

import java.util.function.Function;

public class SegmentationNumberThen<T> extends SegmentationThen<T> {
    Number caseCondition;
    boolean condition;
    T result;

    public SegmentationNumberThen(Number caseCondition, boolean condition, T result) {
        this.caseCondition = caseCondition;
        this.condition = condition;
        this.result = result;
    }

    public SegmentationWhenNumber<T> then(Function<T, T> procedures) {
        if (this.condition) {
            return new SegmentationWhenNumber<T>(caseCondition, this.condition,  procedures.apply(result));
        } else return new SegmentationWhenNumber<T>(caseCondition, this.condition , result);
    }
}
