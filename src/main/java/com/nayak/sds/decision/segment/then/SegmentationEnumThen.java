package com.nayak.sds.decision.segment.then;

import com.nayak.sds.decision.segment.when.SegmentationWhenEnum;

import java.util.function.Function;

public class SegmentationEnumThen<T> extends SegmentationThen<T> {
    Enum caseCondition;
    boolean condition;
    T result;

    public SegmentationEnumThen(Enum caseCondition, boolean condition, T result) {
        this.caseCondition = caseCondition;
        this.condition = condition;
        this.result = result;
    }

    public SegmentationWhenEnum<T> then(Function<T, T> procedures) {
        if (this.condition) {
            return new SegmentationWhenEnum<T>(caseCondition, this.condition,  procedures.apply(result));
        } else return new SegmentationWhenEnum<T>(caseCondition, this.condition , result);
    }
}