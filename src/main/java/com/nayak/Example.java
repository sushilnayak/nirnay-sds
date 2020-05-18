package com.nayak;

public class Example<T> {

    T result;

    Case _case;

    When when;

    Then then;

    public static Example builder() {
        return new Example();
    }

    public Example evaluate(T result) {
        this.result = result;
        return this;
    }

    public Example withCase(Case _case) {
        this._case = _case;
        return this;
    }

    public Example withWhen(When when) {
        this.when = when;
        return this;
    }

    public Example withThen(Then then) {
        this.then = then;
        return this;
    }

    class Then {


        public String function;

        public Then withFunction(String function) {
            this.function = function;
            return this;
        }

    }

    class Case {


        public String withCase;

        public String withStringCase;

        public String withNumberCase;

        public Case withWithCase(String withCase) {
            this.withCase = withCase;
            return this;
        }

        public Case withWithStringCase(String withStringCase) {
            this.withStringCase = withStringCase;
            return this;
        }

        public Case withWithNumberCase(String withNumberCase) {
            this.withNumberCase = withNumberCase;
            return this;
        }
    }

    class When {


        public String _boolean;

        public String predicate;

        public When withBoolean(String _boolean) {
            this._boolean = _boolean;
            return this;
        }

        public When withPredicate(String predicate) {
            this.predicate = predicate;
            return this;
        }

    }

}
