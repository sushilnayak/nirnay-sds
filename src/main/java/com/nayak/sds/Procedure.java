package com.nayak.sds;

import java.util.function.UnaryOperator;

public interface Procedure<T> {
    T apply(T t);

    static <T> Procedure<T> builder(T other) {
        return (T t) -> other;
    }

    public default Procedure<T> compose(UnaryOperator<T> f) {
        return (T t) -> {
            T initial = this.apply(t);
            return f.apply(initial);
        };
    }

    default T build() {
        return this.build();
    }
}
