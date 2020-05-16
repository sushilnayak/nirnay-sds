package com.nayak.sds.procedure;

public interface Procedure<T, R> {
    T apply(T t);
}
