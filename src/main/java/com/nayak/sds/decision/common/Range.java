package com.nayak.sds.decision.common;

import com.nayak.sds.decision.exception.RangeException;

import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Range utility class which provides helper method to be used in when statement range evaluation in
 */
public class Range {

    private Range() {
    }

    /**
     * Range helper method for int primitive type
     *
     * @param start     int value of start of the range
     * @param stop      int value of end of the range
     * @param rangeType rangeType for the start & stop
     * @return IntPredicate
     */
    public static IntPredicate between(int start, int stop, RangeType rangeType) {
        if (start >= stop)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        Objects.requireNonNull(rangeType, "RangeType cannot be null");
        //@formatter:off
             if (RangeType.INCLUSIVE.equals(rangeType))            return value -> start <= value && value <= stop;
        else if (RangeType.EXCLUSIVE.equals(rangeType))            return value -> start < value && value < stop;
        else if (RangeType.LEFT_INCLUSIVE_ONLY.equals(rangeType))  return value -> start <= value && value < stop;
        else if (RangeType.RIGHT_INCLUSIVE_ONLY.equals(rangeType)) return value -> start < value && value <= stop;
        //@formatter:on
        return value -> start <= value && value >= stop;
    }

    /**
     * Range helper method for int primitive type w/ both range start & stop inclusive in range evaluation
     *
     * @param start int value of start of the range
     * @param stop  int value of end of the range
     * @return IntPredicate
     */
    public static IntPredicate between(int start, int stop) {
        if (start >= stop)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        return between(start, stop, RangeType.INCLUSIVE);
    }

    /**
     * Range helper method for long primitive type
     *
     * @param start     long value of start of the range
     * @param stop      long value of end of the range
     * @param rangeType rangeType for the start & stop
     * @return LongPredicate
     */
    public static LongPredicate between(long start, long stop, RangeType rangeType) {
        if (start >= stop)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        Objects.requireNonNull(rangeType, "RangeType cannot be null");
        //@formatter:off
             if (RangeType.INCLUSIVE.equals(rangeType))            return value -> start <= value && value <= stop;
        else if (RangeType.EXCLUSIVE.equals(rangeType))            return value -> start < value && value < stop;
        else if (RangeType.LEFT_INCLUSIVE_ONLY.equals(rangeType))  return value -> start <= value && value < stop;
        else if (RangeType.RIGHT_INCLUSIVE_ONLY.equals(rangeType)) return value -> start < value && value <= stop;
        //@formatter:on
        return value -> start <= value && value >= stop;
    }

    /**
     * Range helper method for long primitive type w/ both range start & stop inclusive in range evaluation
     *
     * @param start long value of start of the range
     * @param stop  long value of end of the range
     * @return LongPredicate
     */
    public static LongPredicate between(long start, long stop) {
        if (start >= stop)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        return between(start, stop, RangeType.INCLUSIVE);
    }

    /**
     * Range helper method for double primitive type
     *
     * @param start     double value of start of the range
     * @param stop      double value of end of the range
     * @param rangeType rangeType for the start & stop
     * @return DoublePredicate
     */
    public static DoublePredicate between(double start, double stop, RangeType rangeType) {
        if (start >= stop)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        Objects.requireNonNull(rangeType, "RangeType cannot be null");
        //@formatter:off
             if (RangeType.INCLUSIVE.equals(rangeType))            return value -> start <= value && value <= stop;
        else if (RangeType.EXCLUSIVE.equals(rangeType))            return value -> start < value && value < stop;
        else if (RangeType.LEFT_INCLUSIVE_ONLY.equals(rangeType))  return value -> start <= value && value < stop;
        else if (RangeType.RIGHT_INCLUSIVE_ONLY.equals(rangeType)) return value -> start < value && value <= stop;
        //@formatter:on
        return value -> start <= value && value >= stop;
    }

    /**
     * Range helper method for double primitive type w/ both range start & stop inclusive in range evaluation
     *
     * @param start double value of start of the range
     * @param stop  double value of end of the range
     * @return DoublePredicate
     */
    public static DoublePredicate between(double start, double stop) {
        if (start >= stop)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        return between(start, stop, RangeType.INCLUSIVE);
    }

    public static <T extends Number & Comparable<? super T>> Predicate<T> between(T start, T stop, RangeType rangeType) {
        int comp = start.compareTo(stop);
        if (comp == 0 || comp > 0)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        Objects.requireNonNull(rangeType, "RangeType cannot be null");
        //@formatter:off
             if (RangeType.INCLUSIVE.equals(rangeType))            return value -> start.compareTo(value) <= 0 && value.compareTo(stop) <= 0;
        else if (RangeType.EXCLUSIVE.equals(rangeType))            return value -> start.compareTo(value) <  0 && value.compareTo(stop) < 0;
        else if (RangeType.LEFT_INCLUSIVE_ONLY.equals(rangeType))  return value -> start.compareTo(value) <=  0 && value.compareTo(stop) < 0;
        else if (RangeType.RIGHT_INCLUSIVE_ONLY.equals(rangeType)) return value -> start.compareTo(value) <  0 && value.compareTo(stop) <= 0;
        //@formatter:on
        return value -> start.compareTo(value) <= 0 && value.compareTo(stop) <= 0;
    }

    public static <T extends Number & Comparable<? super T>> Predicate<T> between(T start, T stop) {
        int comp = start.compareTo(stop);
        if (comp == 0 || comp > 0)
            throw new RangeException("Start should be smaller than Stop value. Equality Check is supported w/o Range");
        return between(start, stop, RangeType.INCLUSIVE);
    }

    public static Predicate<String> between(String start, String stop, RangeType rangeType) {
        if (start.length() != 1 || stop.length() != 1)
            throw new RangeException("String Range was created to cater to single Character value. To avoid conversion cost if request stores single char as String");
        Objects.requireNonNull(rangeType, "RangeType cannot be null");
        //@formatter:off
             if (RangeType.INCLUSIVE.equals(rangeType))            return value -> start.compareTo(value) <= 0 && value.compareTo(stop) <= 0;
        else if (RangeType.EXCLUSIVE.equals(rangeType))            return value -> start.compareTo(value) <  0 && value.compareTo(stop) < 0;
        else if (RangeType.LEFT_INCLUSIVE_ONLY.equals(rangeType))  return value -> start.compareTo(value) <=  0 && value.compareTo(stop) < 0;
        else if (RangeType.RIGHT_INCLUSIVE_ONLY.equals(rangeType)) return value -> start.compareTo(value) <  0 && value.compareTo(stop) <= 0;
        //@formatter:on
        return value -> start.compareTo(value) <= 0 && value.compareTo(stop) <= 0;
    }

    public static Predicate<String> between(String start, String stop) {
        if (start.length() != 1 || stop.length() != 1)
            throw new RangeException("String Range was created to cater to single Character value. To avoid conversion cost if request stores single char as String");
        return between(start, stop, RangeType.INCLUSIVE);
    }
}
