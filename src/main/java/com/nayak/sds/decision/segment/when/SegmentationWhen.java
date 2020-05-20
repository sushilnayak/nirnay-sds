package com.nayak.sds.decision.segment.when;

import com.nayak.sds.Workflow;
import com.nayak.sds.decision.segment.build.SegmentationBuild;

import java.util.function.UnaryOperator;

public class SegmentationWhen<T> {
    private T result;
    private boolean otherwiseCondition;

    SegmentationWhen(T result, boolean otherwiseCondition) {
        Workflow.path.add(Thread.currentThread().getStackTrace()[1].getMethodName());
        this.result = result;
        this.otherwiseCondition = otherwiseCondition;
    }

    public SegmentationBuild<T> otherwise(UnaryOperator<T> procedures) {
        if (otherwiseCondition) {
            T apply = procedures.apply(result);
            return new SegmentationBuild<>(apply);
        } else {
            return new SegmentationBuild<>(result);
        }
    }

    public T build() {
        return new SegmentationBuild<T>(result).build();
    }


}