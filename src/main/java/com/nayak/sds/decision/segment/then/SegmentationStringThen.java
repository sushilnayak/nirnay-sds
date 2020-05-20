package com.nayak.sds.decision.segment.then;

import com.nayak.sds.decision.segment.when.SegmentationWhenString;

import java.util.function.Function;

public class SegmentationStringThen<T> extends SegmentationThen<T> {
    String caseCondition;
    boolean condition;
    T result;

    public SegmentationStringThen(String caseCondition, boolean condition, T result) {
        this.caseCondition = caseCondition;
        this.condition = condition;
        this.result = result;
    }

    public SegmentationWhenString<T> then(Function<T, T> procedures) {
        if (this.condition) {
            return new SegmentationWhenString<T>(caseCondition, this.condition,  procedures.apply(result));
        } else return new SegmentationWhenString<T>(caseCondition, this.condition , result);
    }
}