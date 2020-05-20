package com.nayak.sds.decision.segment.build;

public class SegmentationBuild<T> {
    private T result;

    public SegmentationBuild(T result) {
        this.result = result;
    }

    public T build() {
        return (T) result;
    }

}