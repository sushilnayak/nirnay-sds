package com.nayak.sds.decision.segment.when;

import com.nayak.sds.decision.segment.then.SegmentationStringThen;

import java.util.function.Predicate;

public class SegmentationWhenString<T> extends SegmentationWhen<T> {
    private T result;
    private String caseCondition;

    public SegmentationWhenString(String caseCondition, boolean conditionEvaluation, T result) {
        super(result, !conditionEvaluation);
        this.result = result;
        this.caseCondition = caseCondition;
    }

    public SegmentationStringThen<T> when(String predicate) {
        if (caseCondition.equals(predicate)) return new SegmentationStringThen<T>(caseCondition, true, result);
        return new SegmentationStringThen<T>(caseCondition, false, result);
    }

    public SegmentationStringThen<T> when(Predicate<T> predicate) {
        boolean condition = predicate.test(result);
        return new SegmentationStringThen<T>(caseCondition, condition, result);
    }
}