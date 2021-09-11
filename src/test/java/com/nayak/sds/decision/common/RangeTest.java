package com.nayak.sds.decision.common;

import org.junit.jupiter.api.Test;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import static com.nayak.sds.decision.common.Range.between;
import static org.assertj.core.api.Assertions.assertThat;

class RangeTest {

    @Test
    void intRangeOutsideRangeTest() {
        IntPredicate between = between(1, 10);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void intRangeInclusiveRangeTest() {
        IntPredicate between = between(1, 10);
        assertThat(between.test(1)).isTrue();
        assertThat(between.test(10)).isTrue();
    }

    @Test
    void intRangeWithinRangeTest() {
        IntPredicate between = between(1, 10);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }

    @Test
    void intRangeOutsideRangeWithExclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.EXCLUSIVE);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(10)).isFalse();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void intRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.EXCLUSIVE);
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(9)).isTrue();
        assertThat(between.test(10)).isFalse();
    }

    @Test
    void intRangeWithinRangeWithExclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.EXCLUSIVE);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }

    @Test
    void intRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(1)).isTrue();
        assertThat(between.test(10)).isFalse();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void intRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(1)).isTrue();
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(9)).isTrue();
        assertThat(between.test(10)).isFalse();
    }

    @Test
    void intRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }

    @Test
    void intRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(10)).isTrue();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void intRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(9)).isTrue();
        assertThat(between.test(10)).isTrue();
    }

    @Test
    void intRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        IntPredicate between = between(1, 10, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }


    @Test
    void longRangeOutsideRangeTest() {
        LongPredicate between = between(1L, 10L);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longRangeInclusiveRangeTest() {
        LongPredicate between = between(1L, 10L);
        assertThat(between.test(1L)).isTrue();
        assertThat(between.test(10L)).isTrue();
    }

    @Test
    void longRangeWithinRangeTest() {
        LongPredicate between = between(1L, 10L);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }

    @Test
    void longRangeOutsideRangeWithExclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.EXCLUSIVE);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(10L)).isFalse();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.EXCLUSIVE);
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(9L)).isTrue();
        assertThat(between.test(10L)).isFalse();
    }

    @Test
    void longRangeWithinRangeWithExclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.EXCLUSIVE);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }

    @Test
    void longRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(1L)).isTrue();
        assertThat(between.test(10L)).isFalse();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(1L)).isTrue();
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(9L)).isTrue();
        assertThat(between.test(10L)).isFalse();
    }

    @Test
    void longRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }

    @Test
    void longRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(10L)).isTrue();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(9L)).isTrue();
        assertThat(between.test(10L)).isTrue();
    }

    @Test
    void longRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        LongPredicate between = between(1L, 10L, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }


    @Test
    void doubleRangeOutsideRangeTest() {
        DoublePredicate between = between(1D, 10D);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleRangeInclusiveRangeTest() {
        DoublePredicate between = between(1D, 10D);
        assertThat(between.test(1D)).isTrue();
        assertThat(between.test(10D)).isTrue();
    }

    @Test
    void doubleRangeWithinRangeTest() {
        DoublePredicate between = between(1D, 10D);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }

    @Test
    void doubleRangeOutsideRangeWithExclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.EXCLUSIVE);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(10D)).isFalse();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.EXCLUSIVE);
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(9D)).isTrue();
        assertThat(between.test(10D)).isFalse();
    }

    @Test
    void doubleRangeWithinRangeWithExclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.EXCLUSIVE);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }

    @Test
    void doubleRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(1D)).isTrue();
        assertThat(between.test(10D)).isFalse();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(1D)).isTrue();
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(9D)).isTrue();
        assertThat(between.test(10D)).isFalse();
    }

    @Test
    void doubleRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }

    @Test
    void doubleRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(10D)).isTrue();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(9D)).isTrue();
        assertThat(between.test(10D)).isTrue();
    }

    @Test
    void doubleRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        DoublePredicate between = between(1D, 10D, RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }


    // Object testing starting


    @Test
    void integerRangeOutsideRangeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10));
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void integerRangeInclusiveRangeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10));
        assertThat(between.test(1)).isTrue();
        assertThat(between.test(10)).isTrue();
    }

    @Test
    void integerRangeWithinRangeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10));
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }

    @Test
    void integerRangeOutsideRangeWithExclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.EXCLUSIVE);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(10)).isFalse();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void integerRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.EXCLUSIVE);
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(9)).isTrue();
        assertThat(between.test(10)).isFalse();
    }

    @Test
    void integerRangeWithinRangeWithExclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.EXCLUSIVE);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }

    @Test
    void integerRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(1)).isTrue();
        assertThat(between.test(10)).isFalse();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void integerRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(1)).isTrue();
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(9)).isTrue();
        assertThat(between.test(10)).isFalse();
    }

    @Test
    void integerRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }

    @Test
    void integerRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(0)).isFalse();
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(10)).isTrue();
        assertThat(between.test(11)).isFalse();
    }


    @Test
    void integerRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(1)).isFalse();
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(9)).isTrue();
        assertThat(between.test(10)).isTrue();
    }

    @Test
    void integerRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Integer> between = between(Integer.valueOf(1), Integer.valueOf(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(2)).isTrue();
        assertThat(between.test(5)).isTrue();
        assertThat(between.test(9)).isTrue();
    }


    @Test
    void longObjectRangeOutsideRangeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10));
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longObjectRangeInclusiveRangeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10));
        assertThat(between.test(1L)).isTrue();
        assertThat(between.test(10L)).isTrue();
    }

    @Test
    void longObjectRangeWithinRangeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10));
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }

    @Test
    void longObjectRangeOutsideRangeWithExclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.EXCLUSIVE);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(10L)).isFalse();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longObjectRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.EXCLUSIVE);
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(9L)).isTrue();
        assertThat(between.test(10L)).isFalse();
    }

    @Test
    void longObjectRangeWithinRangeWithExclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.EXCLUSIVE);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }

    @Test
    void longObjectRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(1L)).isTrue();
        assertThat(between.test(10L)).isFalse();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longObjectRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(1L)).isTrue();
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(9L)).isTrue();
        assertThat(between.test(10L)).isFalse();
    }

    @Test
    void longObjectRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }

    @Test
    void longObjectRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(0L)).isFalse();
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(10L)).isTrue();
        assertThat(between.test(11L)).isFalse();
    }


    @Test
    void longObjectRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(1L)).isFalse();
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(9L)).isTrue();
        assertThat(between.test(10L)).isTrue();
    }

    @Test
    void longObjectRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Long> between = between(new Long(1), new Long(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(2L)).isTrue();
        assertThat(between.test(5L)).isTrue();
        assertThat(between.test(9L)).isTrue();
    }


    @Test
    void doubleObjectRangeOutsideRangeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10));
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleObjectRangeInclusiveRangeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10));
        assertThat(between.test(1D)).isTrue();
        assertThat(between.test(10D)).isTrue();
    }

    @Test
    void doubleObjectRangeWithinRangeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10));
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }

    @Test
    void doubleObjectRangeOutsideRangeWithExclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.EXCLUSIVE);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(10D)).isFalse();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleObjectRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.EXCLUSIVE);
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(9D)).isTrue();
        assertThat(between.test(10D)).isFalse();
    }

    @Test
    void doubleObjectRangeWithinRangeWithExclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.EXCLUSIVE);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }

    @Test
    void doubleObjectRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(1D)).isTrue();
        assertThat(between.test(10D)).isFalse();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleObjectRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(1D)).isTrue();
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(9D)).isTrue();
        assertThat(between.test(10D)).isFalse();
    }

    @Test
    void doubleObjectRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }

    @Test
    void doubleObjectRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(0D)).isFalse();
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(10D)).isTrue();
        assertThat(between.test(11D)).isFalse();
    }


    @Test
    void doubleObjectRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(1D)).isFalse();
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(9D)).isTrue();
        assertThat(between.test(10D)).isTrue();
    }

    @Test
    void doubleObjectRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        Predicate<Double> between = between(Double.valueOf(1), Double.valueOf(10), RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test(2D)).isTrue();
        assertThat(between.test(5D)).isTrue();
        assertThat(between.test(9D)).isTrue();
    }


    @Test
    void stringObjectRangeOutsideRangeTest() {
        Predicate<String> between = between("B", "F");
        assertThat(between.test("A")).isFalse();
        assertThat(between.test("G")).isFalse();
    }


    @Test
    void stringObjectRangeInclusiveRangeTest() {
        Predicate<String> between = between("B", "F");
        assertThat(between.test("B")).isTrue();
        assertThat(between.test("F")).isTrue();
    }

    @Test
    void stringObjectRangeWithinRangeTest() {
        Predicate<String> between = between("B", "F");
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("D")).isTrue();
        assertThat(between.test("E")).isTrue();
    }

    @Test
    void stringObjectRangeOutsideRangeWithExclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.EXCLUSIVE);
        assertThat(between.test("A")).isFalse();
        assertThat(between.test("B")).isFalse();
        assertThat(between.test("F")).isFalse();
        assertThat(between.test("G")).isFalse();
    }


    @Test
    void stringObjectRangeInclusiveRangeWithExclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.EXCLUSIVE);
        assertThat(between.test("B")).isFalse();
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("E")).isTrue();
        assertThat(between.test("F")).isFalse();
    }

    @Test
    void stringObjectRangeWithinRangeWithExclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.EXCLUSIVE);
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("D")).isTrue();
        assertThat(between.test("E")).isTrue();
    }

    @Test
    void stringObjectRangeOutsideRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test("A")).isFalse();
        assertThat(between.test("B")).isTrue();
        assertThat(between.test("F")).isFalse();
        assertThat(between.test("G")).isFalse();
    }


    @Test
    void stringObjectRangeInclusiveRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test("B")).isTrue();
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("E")).isTrue();
        assertThat(between.test("F")).isFalse();
    }

    @Test
    void stringObjectRangeWithinRangeWithLeftInclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.LEFT_INCLUSIVE_ONLY);
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("D")).isTrue();
        assertThat(between.test("E")).isTrue();
    }

    @Test
    void stringObjectRangeOutsideRangeWithRightInclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test("A")).isFalse();
        assertThat(between.test("B")).isFalse();
        assertThat(between.test("F")).isTrue();
        assertThat(between.test("G")).isFalse();
    }


    @Test
    void stringObjectRangeInclusiveRangeWithRightInclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test("B")).isFalse();
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("E")).isTrue();
        assertThat(between.test("F")).isTrue();
    }

    @Test
    void stringObjectRangeWithinRangeWithRightInclusiveRangeTypeTest() {
        Predicate<String> between = between("B", "F", RangeType.RIGHT_INCLUSIVE_ONLY);
        assertThat(between.test("C")).isTrue();
        assertThat(between.test("D")).isTrue();
        assertThat(between.test("E")).isTrue();
    }

}