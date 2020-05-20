package com.nayak;

public interface Summer<T> {
    T apply(T t);

    static <T> Summer<T> start(T start) {
        return (T t) -> {
//            System.out.println("" + start  + " + " + t);
            System.out.println("" + t + start);
            return start;
        };
    }

    default Summer<T> then1(T other) {
        return (T t) -> {
//            System.out.println("" + t + " * " + other);
            T apply = this.apply(t);
            System.out.println("" + t + apply + other);
//            System.out.println(apply);
            return other;
        };
    }

    default Summer<T> then2(T other) {
        return (T t) -> {
//            System.out.println("" + other  + " - " + t);
            T apply = this.apply(t);
//            System.out.println(apply);
            System.out.println("" + t + apply + other);
            return other;
        };
    }


}
