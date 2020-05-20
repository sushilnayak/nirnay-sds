package com.nayak.sds.decision.segment.when;

import com.nayak.sds.decision.segment.then.SegmentationEnumThen;

import java.util.function.Predicate;

public class SegmentationWhenEnum<T> extends SegmentationWhen<T> {
    private T result;
    private Enum caseCondition;

    public SegmentationWhenEnum(Enum caseCondition, boolean conditionEvaluation,  T result) {
        super(result, !conditionEvaluation);
        this.result = result;
        this.caseCondition = caseCondition;
    }

    public SegmentationEnumThen<T> when(Enum predicate) {
        if (caseCondition == predicate) return new SegmentationEnumThen<T>(caseCondition, true, result);
        return new SegmentationEnumThen<T>(caseCondition, false, result);
    }

    public SegmentationEnumThen<T> when(Predicate<T> predicate) {
        boolean condition = predicate.test(result);
        return new SegmentationEnumThen<T>(caseCondition, condition, result);
    }
}