package com.nayak.sds.decision.segment;

import com.nayak.sds.Workflow;
import com.nayak.sds.decision.Procedure;

import java.util.Objects;
import java.util.function.*;

/**
 * Segmentation would be used to drive decision based on categorical/numerical/predicate conditions
 *
 * @param <T> Type of the input data type for driving the segmentation
 */
public class Segmentation<T> {

    private String segmentationName;
    private final T result;

    /**
     * Constructor for Segmentation. To be used for internal purpose and not to be exposed to client
     *
     * @param result state of the segmentation
     */
    private Segmentation(T result) {
        this.result = result;
    }

    /**
     * This would provide input object for the segmentation
     *
     * @param inputData input object that would be driving segmentation
     * @param <T>       type of input object
     * @return Segmentation object
     */
    public static <T> Segmentation<T> using(T inputData) {
        return new Segmentation<>(inputData);
    }

    /**
     * This provides the result object and is to be called after build() method
     *
     * @return result object
     */
    public T get() {
        return result;
    }

    /**
     * This provides name to the Segmentation
     *
     * @param name name of the segmentation
     * @return SegmentationUsing which would have .using() method
     */
    public SegmentationUsing<T> name(String name) {
        this.segmentationName = name;
        return new SegmentationUsing<>(result);
    }
//    interface PrimitiveIntRange<T> extends Function<T, Integer>{}

    public static class SegmentationUsing<T> {
        private final T result;

        SegmentationUsing(T result) {
            this.result = result;
        }

        public <V> SegmentationWhen<T, V> withCase(Function<? super T, V> whenCondition) {
            Objects.requireNonNull(whenCondition, "When Condition cannot be null");
            return new SegmentationWhen<T, V>(result, whenCondition, true);
        }

//        public SegmentationWhen<T, IntPredicate> withCase(PrimitiveIntRange<T> whenCondition){
//           return null;
//        }


        public SegmentationWhen<T, Boolean> withCase(Predicate<? super T> whenPredicate) {
            Objects.requireNonNull(whenPredicate, "Predicate for withBooleanCase cannot be null");
            return new SegmentationWhen<T, Boolean>(result, whenPredicate::test, true);
        }

        public SegmentationWhen<T, Predicate<T>> withCase() {
            Predicate<T> predicate = t -> false;
            return new SegmentationWhen<T, Predicate<T>>(result, t -> predicate, true);
        }
    }

    public static class SegmentationBuild<T> {
        private final T result;

        SegmentationBuild(T result) {
            this.result = result;
        }

        public Segmentation<T> build() {
            return new Segmentation<>(result);
        }

    }

    public static class SegmentationThen<T, R> {
        private T result;
        private final Function<? super T, R> caseFunction;
        private final boolean whenConditionMatched;
        private final boolean otherwiseApplicable;

        SegmentationThen(T result, Function<? super T, R> caseFunction, boolean whenConditionMatched, boolean otherwiseApplicable) {
            this.result = result;
            this.caseFunction = caseFunction;
            this.whenConditionMatched = whenConditionMatched;
            this.otherwiseApplicable = otherwiseApplicable;
        }

        public SegmentationWhen<T, R> then(Procedure<T> procedure) {
            if (this.whenConditionMatched) {
                this.result = procedure.apply(this.result);
            }
            return new SegmentationWhen<>(this.result, this.caseFunction, this.otherwiseApplicable);
        }

        public SegmentationWhen<T, R> thenProcedure(Procedure<T> procedure) {
            if (this.whenConditionMatched) {
                this.result = procedure.apply(this.result);
            }
            return new SegmentationWhen<>(this.result, this.caseFunction, this.otherwiseApplicable);
        }

        public SegmentationWhen<T, R> then(Function<T, Workflow<T>> workflow) {
            if (this.whenConditionMatched) {
                this.result = workflow.apply(this.result).internalGet();
            }
            return new SegmentationWhen<>(this.result, this.caseFunction, this.otherwiseApplicable);
        }

        public SegmentationWhen<T, R> thenWorkflow(Function<T, Workflow<T>> workflow) {
            if (this.whenConditionMatched) {
                this.result = workflow.apply(this.result).internalGet();
            }
            return new SegmentationWhen<>(this.result, this.caseFunction, this.otherwiseApplicable);
        }
    }

    public static class SegmentationWhen<T, R> extends SegmentationBuild<T> {
        private final T result;
        private final Function<? super T, R> caseFunction;
        private final boolean otherwiseApplicable;
        private R cachedCaseValue;

        SegmentationWhen(T result, Function<? super T, R> caseFunction, boolean otherwiseApplicable) {
            super(result);
            this.result = result;
            this.caseFunction = caseFunction;
            this.otherwiseApplicable = otherwiseApplicable;
        }

        private R getCaseValue() {
            if (cachedCaseValue == null) {
                cachedCaseValue = caseFunction.apply(result);
            }
            return cachedCaseValue;
        }

        public SegmentationBuild<T> otherwise(Procedure<T> procedures) {
            if (otherwiseApplicable) {
                T apply = procedures.apply(result);
                return new SegmentationBuild<>(apply);
            } else {
                return new SegmentationBuild<>(result);
            }
        }

        public SegmentationThen<T, R> when(IntPredicate condition) {
            boolean matchingWhen = condition.test((Integer) getCaseValue());
            return new SegmentationThen<>(result, caseFunction, matchingWhen, !matchingWhen && this.otherwiseApplicable);
        }

        public SegmentationThen<T, R> when(LongPredicate condition) {
            boolean matchingWhen = condition.test((Long) getCaseValue());
            return new SegmentationThen<>(result, caseFunction, matchingWhen, !matchingWhen && this.otherwiseApplicable);
        }

        //
        public SegmentationThen<T, R> when(DoublePredicate condition) {
            boolean matchingWhen = false;
            R caseValue = getCaseValue();
            if (caseValue instanceof Integer) {
                matchingWhen = condition.test((Integer) caseValue);
            } else if (caseValue instanceof Double) {
                matchingWhen = condition.test((Double) caseValue);
            } else if (caseValue instanceof Float) {
                matchingWhen = condition.test((Float) caseValue);
            } else if (caseValue instanceof Long) {
                matchingWhen = condition.test((Long) caseValue);
            }
            return new SegmentationThen<>(result, caseFunction, matchingWhen, !matchingWhen && this.otherwiseApplicable);
        }

        public SegmentationThen<T, R> when(R condition) {
            boolean matchingWhen;
            R caseValue = getCaseValue();
            if (condition instanceof Predicate) {
                matchingWhen = ((Predicate) condition).test(result);
            } else {
                matchingWhen = caseValue.equals(condition);
            }
            return new SegmentationThen<>(result, caseFunction, matchingWhen, !matchingWhen && this.otherwiseApplicable);
        }

    }

}
