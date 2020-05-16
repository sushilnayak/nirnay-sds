package com.nayak.sds.segmentation;

import java.util.List;
import java.util.function.Predicate;

/**
 * Segmentation is used to map record based on requirement
 * - {@link SubPopulation} is used for providing boolean value based on condition like {@link java.util.function.Predicate}
 * - {@link ClassSet} is used to for providing categorical value based on condition
 * - {@link Matrix} is used for providing categorical value based on multiple condition. This is similar to {@link ClassSet}, but here were are talking about crosstab kind of implementation
 */
public interface Segmentation<T, R> {
    R apply(T t);

    static <T, R> Segmentation<T, R> condition(Predicate<T> predicate) {
        return (T t) -> {
            return predicate.test(t);
        };
    }
