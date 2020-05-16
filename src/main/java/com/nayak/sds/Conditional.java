//package com.nayak.sds;
//
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//public interface Conditional<T> extends Function<T, T> {
//
//    static <T>  <T> when(Predicate<T> predicate, Workflow<T> workflow) {
//        return t -> {
//            if (predicate.test(t)) {
//                return ;
//            }
//            return null;
//        };
//    }
//
//    default Conditional<T> or(Predicate<T> predicate, Workflow<T> workflow) {
//        return null;
//    }
//
//    default Conditional<T> otherwise(Predicate<T> predicate, Workflow<T> workflow) {
//        return null;
//    }
//}
