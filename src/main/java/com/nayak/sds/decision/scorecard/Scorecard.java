package com.nayak.sds.decision.scorecard;


import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @param <T> Type of the input data type for driving the Scorecard
 */
public class Scorecard<T> {

    private String segmentationName;
    private final T result;

    /**
     * Constructor for scorecard. To be used for internal purpose and not to be exposed to client
     *
     * @param result input object that would be driving scorecard
     */
    private Scorecard(T result) {
        this.result = result;
    }

    /**
     * @param inputData
     * @param <T>
     * @return
     */
    public static <T> Scorecard<T> using(T inputData) {
        return new Scorecard<>(inputData);
    }

    /**
     * @return
     */
    public T get() {
        return result;
    }

    /**
     * @param name
     * @return
     */
    public ScorecardUsing<T> name(String name) {
        this.segmentationName = name;
        return new ScorecardUsing<>(result);
    }

    /**
     * @param <T>
     */
    public static class ScorecardUsing<T> {
        private final T result;

        /**
         * @param result
         */
        ScorecardUsing(T result) {
            this.result = result;
        }

        /**
         * @param whenCondition
         * @param <V>
         * @return
         */
        public <V> ScorecardWhen<T, V> withCase(Function<? super T, V> whenCondition) {
            Objects.requireNonNull(whenCondition, "When Condition cannot be null");

            V caseCondition = whenCondition.apply(result);
            return new ScorecardWhen<>(result, caseCondition, true);
        }

        /**
         * @param whenPredicate
         * @return
         */
        public ScorecardWhen<T, Boolean> withCase(Predicate<? super T> whenPredicate) {
            Objects.requireNonNull(whenPredicate, "Predicate for withBooleanCase cannot be null");

            boolean caseCondition = whenPredicate.test(result);
            return new ScorecardWhen<>(result, caseCondition, true);
        }

        /**
         * @return
         */
        public ScorecardWhen<T, Predicate<T>> withCase() {
            Predicate<T> predicate = t -> false;
            return new ScorecardWhen<>(result, predicate, true);
        }
    }

    /**
     * @param <T>
     */
    public static class ScorecardBuild<T> {
        private final T result;

        /**
         * @param result
         */
        ScorecardBuild(T result) {
            this.result = result;
        }

        /**
         * @return
         */
        public Scorecard<T> build() {
            return new Scorecard<>(result);
        }

    }

    /**
     * @param <T>
     * @param <R>
     */
    public static class ScorecardThen<T, R> {
        private final T result;
        private final R caseCondition;
        private final boolean whenConditionMatched;
        private final boolean otherwiseApplicable;

        /**
         * @param result
         * @param caseCondition
         * @param whenConditionMatched
         * @param otherwiseApplicable
         */
        ScorecardThen(T result, R caseCondition, boolean whenConditionMatched, boolean otherwiseApplicable) {
            this.result = result;
            this.caseCondition = caseCondition;
            this.whenConditionMatched = whenConditionMatched;
            this.otherwiseApplicable = otherwiseApplicable;
        }

        /**
         * @param procedures
         * @return
         */
        public ScorecardWhen<T, R> then(Function<T, T> procedures) {
            return new ScorecardWhen<>(this.whenConditionMatched ? procedures.apply(result) : result, this.caseCondition, this.otherwiseApplicable);
        }
    }

    /**
     * @param <T>
     * @param <R>
     */
    public static class ScorecardWhen<T, R> extends ScorecardBuild<T> {
        private final T result;
        private final R caseCondition;
        private final boolean otherwiseApplicable;

        /**
         * @param result
         * @param caseCondition
         * @param otherwiseApplicable
         */
        ScorecardWhen(T result, R caseCondition, boolean otherwiseApplicable) {
            super(result);
            this.result = result;
            this.caseCondition = caseCondition;
            this.otherwiseApplicable = otherwiseApplicable;
        }

        /**
         * @param procedures
         * @return
         */
        public ScorecardBuild<T> otherwise(Function<T, T> procedures) {
            if (otherwiseApplicable) {
                T apply = procedures.apply(result);
                return new ScorecardBuild<>(apply);
            } else {
                return new ScorecardBuild<>(result);
            }
        }

        /**
         * @param predicate
         * @return
         */
        public ScorecardThen<T, R> when(R predicate) {
            boolean matchingWhen;

            if (predicate instanceof Predicate) {
                matchingWhen = ((Predicate) predicate).test(result);
            } else if(predicate instanceof Enum){
                matchingWhen = caseCondition == predicate;
            } else {
                matchingWhen = caseCondition.equals(predicate);
            }
            return new ScorecardThen<>(result, caseCondition, matchingWhen, !matchingWhen && this.otherwiseApplicable);
        }

    }

}
