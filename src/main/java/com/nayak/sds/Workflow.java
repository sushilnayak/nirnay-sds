package com.nayak.sds;

import com.nayak.sds.decision.Procedure;
import com.nayak.sds.decision.segment.Segmentation;
import lombok.extern.slf4j.Slf4j;

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
        addPath("Workflow(" + name + ")");
        return new Workflow<>(name);
    }

    private static void addPath(String currentMethodName) {
        StringBuilder stringBuilder = WorkflowContextHolder.workflowPath.get();
        stringBuilder.append(" => ").append(currentMethodName);
        WorkflowContextHolder.workflowPath.set(stringBuilder);
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
     * @return T
     * @Deprecated NOTE: This method would be removed in future release. Do not use it
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public T internalGet() {
        return (T) result;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        Objects.requireNonNull(result, "Before get(), we need to add steps for Workflow to work upon");
        log.info(WorkflowContextHolder.workflowPath.get().toString());

        return (T) result;
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
            addPath(Thread.currentThread().getStackTrace()[1].getMethodName());

            return new DataWorkflow<>(init.apply(result));
        }

        /**
         * @param segmentation
         * @return
         */
        public DataWorkflow<T> segmentation(Function<T, Segmentation<T>> segmentation) {
            Objects.requireNonNull(segmentation, "Segmentation Needs to be provided. Cannot be null");
            addPath(Thread.currentThread().getStackTrace()[1].getMethodName());

            return new DataWorkflow<>(segmentation.apply(result).get());
        }

        /**
         * @param procedure
         * @return
         */
        public DataWorkflow<T> process(Procedure<T> procedure) {
            addPath(Thread.currentThread().getStackTrace()[1].getMethodName());

            return new DataWorkflow<>(procedure.apply(this.result));
        }

        /**
         * This would let us log the execution of log
         *
         * @return DataWorkFlow
         */
        public DataWorkflow<T> log() {
            log.info("{}", result);
            return new DataWorkflow<>(result);
        }

        /**
         * @return Workflow
         */
        public Workflow<T> build() {
            return new Workflow<>(result);
        }

    }

}
