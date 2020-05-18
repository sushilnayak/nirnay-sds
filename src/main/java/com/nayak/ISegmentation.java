//package com.nayak;
//
//import java.util.function.Predicate;
//
//public interface ISegmentation<T> {
//
//    T apply(T t);
//
//    public static <T> ISegmentation<T> builder(T object) {
//        return (T t) -> {
//            return object;
//        };
//    }
//
//    public default ISegmentation<T> withBooleanCase(Predicate<T> predicate) {
//        return (T t) -> {
//            boolean test = predicate.test(t);
//
//        };
//    }
//}
