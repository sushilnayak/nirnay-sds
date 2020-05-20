package com.nayak.sds.decision.segment;

import com.nayak.sds.decision.segment.using.SegmentationUsing;

public class Segmentation<T> {

    private String segmentationName;
    private T result;

    private Segmentation(T result) {
        this.result = result;
    }

    public static <T> Segmentation<T> using(T inputData) {
        return new Segmentation<>(inputData);
    }

    public SegmentationUsing<T> name(String name) {
        this.segmentationName = name;
        return new SegmentationUsing<>(result);
    }
}
