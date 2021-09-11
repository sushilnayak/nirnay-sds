package com.nayak.sds;

import com.nayak.sds.decision.Procedure;
import com.nayak.sds.decision.segment.Segmentation;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Function;

/**
 * Workflow is the basic building block
 *
 * @param <T>
 */
@Slf4j
public class Workflow<T> {
    private final Object result;
    static LinkedList<String> path = new LinkedList<>();

    /**
     * @param result
     */
    private Workflow(Object result) {
        this.result = result;
    }

    /**
     * @param name
     * @param <T>
     * @return
     */
    public static <T> Workflow<T> name(String name) {
        Workflow.path.add("Workflow(" + name + ")");
        return new Workflow<>(name);
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public <T> DataWorkflow<T> withData(T data) {
        return new DataWorkflow<>(data);
    }

    /**
     * @param <T>
     */
    public static class DataWorkflow<T> {
        private final T result;

        /**
         * @param result
         */
        DataWorkflow(T result) {
            this.result = result;
        }

        /**
         * @param init
         * @return
         */
        public DataWorkflow<T> initialize(Function<T, T> init) {
            Workflow.path.add(Thread.currentThread().getStackTrace()[1].getMethodName());
            return new DataWorkflow<>(init.apply(result));
        }

        /**
         * @param segmentation
         * @return
         */
        public DataWorkflow<T> segmentation(Function<T, Segmentation<T>> segmentation) {
            Objects.requireNonNull(segmentation, "Segmentation Needs to be provided. Cannot be null");
            Workflow.path.add(Thread.currentThread().getStackTrace()[1].getMethodName());
            return new DataWorkflow<>(segmentation.apply(result).get());
        }

        /**
         * @param procedure
         * @return
         */
        public DataWorkflow<T> process(Procedure<T> procedure) {
            Workflow.path.add(Thread.currentThread().getStackTrace()[1].getMethodName());
            return new DataWorkflow<>(procedure.apply(this.result));
        }

        /**
         * This would let us log the execution of log
         *
         * @return
         */
        public DataWorkflow<T> log() {
            log.info("{}", result);
            return new DataWorkflow<>(result);
        }

        /**
         * @return
         */
        public Workflow<T> build() {
            return new Workflow<>(result);
        }

    }

    /**
     * @return
     * @Deprecated NOTE: This method would be removed in future release. Do not use it
     */
    @Deprecated
    public T internalGet() {
        return (T) result;
    }

    public T get() {
        Objects.requireNonNull(result, "Before get(), we need to add steps for Workflow to work upon");
        log.info(String.join(" -> ", path));
        return (T) result;
    }


}
