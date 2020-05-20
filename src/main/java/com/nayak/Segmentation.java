//package com.nayak;
//
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.function.UnaryOperator;
//
//public class Segmentation<T> {
//    private T result;
//
//
//    private Segmentation(T result) {
//        this.result = result;
//    }
//
//    public static <T> SegmentationUsing<T> using(T person) {
//        return new SegmentationUsing<T>(person);
//    }
//
//
//    static class SegmentationBuild<T> {
//        private T result;
//
//        public SegmentationBuild(T result) {
//            this.result = result;
//        }
//
//        public T build() {
//            return (T) result;
//        }
//
//    }
//
//
//    static class SegmentationThen<T> {
//        private T result;
//
//        public SegmentationThen(T result) {
//            this.result = result;
//        }
//
//
//        public SegmentationWhen<T> then(Function<T, T> procedures) {
//            return new SegmentationWhen<>(result);
//        }
//    }
//
//    static class SegmentationWhen<T> {
//        private T result;
//
//        public SegmentationWhen(T result) {
//            this.result = result;
//        }
//
//        public SegmentationThen<T> when(Predicate<T> predicate) {
//            boolean test = predicate.test(result);
//            return new SegmentationThen<T>(result);
//        }
//
//        public SegmentationBuild<T> otherwise(UnaryOperator<T> procedures) {
//            T apply = procedures.apply(result);
//            return new SegmentationBuild<>(apply);
//        }
//
//    }
//
//    static class SegmentationWhenNumber<T> extends SegmentationWhen<T> {
//        private T result;
//
//        public SegmentationWhenNumber(T result) {
//            super(result);
//            this.result = result;
//        }
//
//        public SegmentationThen<T> when(Number predicate) {
//            return new SegmentationThen<T>(result);
//        }
//
//
//    }
//
//
//    static class SegmentationWhenString<T> extends SegmentationWhen<T> {
//        private T result;
//
//        public SegmentationWhenString(T result) {
//            super(result);
//            this.result = result;
//        }
//
//        public SegmentationThen<T> when(String predicate) {
//            return new SegmentationThen<T>(result);
//        }
//
//    }
//
//    static class SegmentationWhenBoolean<T> extends SegmentationWhen<T> {
//        private T result;
//
//        public SegmentationWhenBoolean(T result) {
//            super(result);
//            this.result = result;
//        }
//
//
//        public SegmentationThen<T> when(boolean predicate) {
//            return new SegmentationThen<T>(result);
//        }
//
//
//    }
//
//    static class SegmentationWhenEnum<T> extends SegmentationWhen<T> {
//        private T result;
//
//        public SegmentationWhenEnum(T result) {
//            super(result);
//            this.result = result;
//        }
//
//        public SegmentationThen<T> when(Enum predicate) {
//            return new SegmentationThen<T>(result);
//        }
//
//    }
//
//    static class SegmentationUsing<T> {
//        private T result;
//
//        public SegmentationUsing(T result) {
//            this.result = result;
//        }
//
//        public SegmentationWhenBoolean<T> withBooleanCase(Predicate<T> predicate) {
//            boolean test = predicate.test(result);
//            return new SegmentationWhenBoolean<T>(result);
//        }
//
//        public SegmentationWhenString<T> withStringCase(Function<T, String> function) {
//            String apply = function.apply(result);
//            return new SegmentationWhenString<T>(result);
//        }
//
//        public SegmentationWhenNumber<T> withNumberCase(Function<T, Number> function) {
//            Number apply = function.apply(result);
//            return new SegmentationWhenNumber<T>(result);
//        }
//
//        public SegmentationWhenEnum<T> withEnumCase(Function<T, Enum> function) {
//            Enum apply = function.apply(result);
//            return new SegmentationWhenEnum<T>(result);
//        }
//    }
//
//}