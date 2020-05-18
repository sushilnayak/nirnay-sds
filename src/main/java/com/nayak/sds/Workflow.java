package com.nayak.sds;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class Workflow<T> {
    Object result;
    private static LinkedList<String> path = new LinkedList<>();

    private Workflow(Object result) {
        this.result = result;
    }

    public Workflow<T> name(String name) {
        path.addFirst("Workflow(" + name + ")");
        return new Workflow<>((T) result);
    }

    public static <T> Workflow<T> supplyData(Supplier<T> supplier) {
        path.add(Thread.currentThread().getStackTrace()[1].getMethodName());

        return new Workflow<>(supplier.get());
    }

    public Workflow<T> initialization(Function<T, T> init) {
        path.add(Thread.currentThread().getStackTrace()[1].getMethodName());

        return new Workflow<>(init.apply((T) result));
    }

    public Workflow<T> decision(Function<T, T> function) {

        return new Workflow<>((T) result);

    }

    public T build() {
        log.info(String.join(" -> ", path));

        return (T) result;
    }

    public Workflow<T> log() {
        log.info("{}", (T) result);
        return new Workflow<>((T) result);
    }


}
