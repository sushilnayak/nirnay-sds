package com.nayak.sds;

import com.nayak.sds.procedure.Procedure;
import com.nayak.sds.segmentation.Segmentation;
import com.nayak.sds.segmentation.SubPopulation;
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


    public Workflow<T> statelessSegmentation(SubPopulation<T> segmentation, LinkedList<Procedure> trueProcedures, LinkedList<Procedure> falseProcedure) {
        if (Boolean.FALSE.equals(segmentation.apply((T) result))) {

            for (int i = 0; i < trueProcedures.size(); i++) {
                Procedure procedure = trueProcedures.get(i);
                procedure.apply(result);
            }
            return new Workflow<>(result);
        }


//        T apply = segmentation.apply((T) result);
        path.add(Thread.currentThread().getStackTrace()[1].getMethodName() + "(" + segmentation.getName() + ")");
        return new Workflow<>(result);
    }

    public Workflow<T> statefullSegmentation(SubPopulation<T> segmentation, LinkedList<Procedure> procedures) {
        path.add(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new Workflow<>(result);
    }


    public T build() {
        log.info(String.join(" -> ", path));

        return (T) result;
    }

    public Workflow<T> log() {
        log.info("{}", (T) result);
        return new Workflow<>((T) result);
    }


    public Workflow<T> segementation(SubPopulation<T> t) {
        t.apply()
    }
}
