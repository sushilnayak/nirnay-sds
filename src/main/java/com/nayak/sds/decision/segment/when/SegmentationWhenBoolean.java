package com.nayak.sds.decision.segment.when;

import com.nayak.sds.decision.segment.then.SegmentationBooleanThen;

import java.util.function.Predicate;

public class SegmentationWhenBoolean<T> extends SegmentationWhen<T> {
    private T result;
    private boolean caseCondition;

    public SegmentationWhenBoolean(boolean caseCondition, boolean conditionEvaluation,  T result) {
        super(result, !conditionEvaluation);
        this.result = result;
        this.caseCondition = caseCondition;
    }

    public SegmentationBooleanThen<T> when(boolean predicate) {
        if (caseCondition == predicate) return new SegmentationBooleanThen<T>(caseCondition, true, result);
        return new SegmentationBooleanThen<T>(caseCondition, false, result);
    }

    public SegmentationBooleanThen<T> when(Predicate<T> predicate) {
        boolean condition = predicate.test(result);
        return new SegmentationBooleanThen<T>(caseCondition, condition, result);
    }

}