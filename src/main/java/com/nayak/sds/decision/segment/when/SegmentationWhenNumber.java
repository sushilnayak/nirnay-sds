package com.nayak.sds.decision.segment.when;

import com.nayak.sds.decision.segment.then.SegmentationNumberThen;

import java.util.function.Predicate;

public class SegmentationWhenNumber<T> extends SegmentationWhen<T> {
    private T result;
    private Number caseCondition;

    public SegmentationWhenNumber(Number caseCondition, boolean conditionEvaluation, T result) {
        super(result, !conditionEvaluation);
        this.result = result;
        this.caseCondition = caseCondition;
    }

    public SegmentationNumberThen<T> when(Number predicate) {
        if (caseCondition.equals(predicate)) return new SegmentationNumberThen<T>(caseCondition, true, result);
        return new SegmentationNumberThen<T>(caseCondition, false, result);
    }

    public SegmentationNumberThen<T> when(Predicate<T> predicate) {
        boolean condition = predicate.test(result);
        return new SegmentationNumberThen<T>(caseCondition, condition, result);
    }
}