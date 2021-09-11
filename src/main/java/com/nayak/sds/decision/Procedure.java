package com.nayak.sds.decision;

/**
 * Procedure is replica of Process in SDS. This helps in chaining execution of function on hte object
 *
 * @param <T> data type of the input object
 */
public interface Procedure<T> {
    T apply(T t);

    /**
     * First Procedure/Process to be applied/executed
     *
     * @param procedure
     * @param <T>
     * @return
     */
    static <T> Procedure<T> then(ProcedureFunction<T> procedure) {
        return procedure::apply;
    }

    /**
     * Next Procedure to be applied/executed
     *
     * @param procedure
     * @return
     */
    default Procedure<T> andThen(ProcedureFunction<T> procedure) {
        return (T t) -> {
            T apply = this.apply(t);
            return procedure.apply(apply);
        };
    }

    /**
     * @param <T>
     */
    interface ProcedureFunction<T> {
        T apply(T t);
    }

}