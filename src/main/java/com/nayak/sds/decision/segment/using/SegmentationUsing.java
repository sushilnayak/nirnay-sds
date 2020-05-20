package com.nayak.sds.decision.segment.using;

import com.nayak.sds.decision.segment.when.*;

import java.util.function.Function;
import java.util.function.Predicate;

public class SegmentationUsing<T> {
    private T result;

    public SegmentationUsing(T result) {
        this.result = result;
    }

    public SegmentationWhenBoolean<T> withBooleanCase(Predicate<T> predicate) {
        boolean caseCondition = predicate.test(result);
        return new SegmentationWhenBoolean<T>(caseCondition, false,result);
    }

    public SegmentationWhenString<T> withStringCase(Function<T, String> function) {
        String caseCondition = function.apply(result);
        return new SegmentationWhenString<T>(caseCondition, false,result);
    }

    public SegmentationWhenNumber<T> withNumberCase(Function<T, Number> function) {
        Number caseCondition = function.apply(result);
        return new SegmentationWhenNumber<T>(caseCondition, false,result);
    }

    public SegmentationWhenEnum<T> withEnumCase(Function<T, Enum> function) {
        Enum caseCondition = function.apply(result);
        return new SegmentationWhenEnum<T>(caseCondition, false,  result);
    }
}