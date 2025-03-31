package com.nayak.sds.decision.scorecard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Scorecard<T, C> {
    private final T targetObject;  // The object being worked on (e.g., Person)
    private final List<Condition<T, C>> conditions = new ArrayList<>();  // Store conditions
    private String name;
    private Function<T, C> caseExtractor;  // Used for extracting the 'case' value (generic type C)
    private Consumer<T> otherwiseConsumer;  // The 'otherwise' action
    private C cachedCaseValue;  // Cache for the case value

    // Private constructor to initialize targetObject
    private Scorecard(T targetObject) {
        this.targetObject = targetObject;
    }

    // Static factory method to start ScoreCard with a target object
    public static <T> Scorecard<T, ?> using(T targetObject) {
        return new Scorecard<>(targetObject);
    }

    // Method to set the name (fluent style)
    public Scorecard<T, C> name(String name) {
        this.name = name;
        return this;
    }

    // Method to set which 'case' (e.g., age, name, etc.) will be evaluated (supports any type C)
    public <C> Scorecard<T, C> withCase(Function<T, C> caseExtractor) {
        Scorecard<T, C> newCard = new Scorecard<>(this.targetObject);  // Create a new instance with updated caseExtractor type
        newCard.caseExtractor = caseExtractor;
        return newCard;
    }

    // Method to specify a 'when' condition (returns ConditionBuilder to allow chaining 'then')
    public ConditionBuilder<T, C> when(C value) {
        return new ConditionBuilder<>(this, caseExtractor, value);
    }

    // Method to define the 'otherwise' condition (what happens if no 'when' condition matches)
    public Scorecard<T, C> otherwise(Consumer<T> consumer) {
        this.otherwiseConsumer = consumer;
        return this;
    }

    // Builds and applies the conditions to the targetObject
    public T build() {
        boolean conditionMet = false;

        // Evaluate all conditions
        for (Condition<T, C> condition : conditions) {
            if (condition.predicate.test(targetObject)) {
                condition.action.accept(targetObject);
                conditionMet = true;
                break;  // Stop once a condition matches
            }
        }

        // Apply 'otherwise' if no condition matches
        if (!conditionMet && otherwiseConsumer != null) {
            otherwiseConsumer.accept(targetObject);
        }

        return targetObject;  // Return the modified object
    }

    // Method for the ConditionBuilder to add conditions
    private void addCondition(Predicate<T> predicate, Consumer<T> action) {
        conditions.add(new Condition<>(predicate, action));
    }

    // Inner class to represent a condition (predicate + action)
    private static class Condition<T, C> {
        Predicate<T> predicate;
        Consumer<T> action;

        Condition(Predicate<T> predicate, Consumer<T> action) {
            this.predicate = predicate;
            this.action = action;
        }
    }

    // Inner class to handle 'when' -> 'then' chaining
    public static class ConditionBuilder<T, C> {
        private final Scorecard<T, C> scoreCard;
        private final Function<T, C> caseExtractor;
        private final C value;

        ConditionBuilder(Scorecard<T, C> scoreCard, Function<T, C> caseExtractor, C value) {
            this.scoreCard = scoreCard;
            this.caseExtractor = caseExtractor;
            this.value = value;
        }

        // Method to specify the action for the current 'when' condition
        public Scorecard<T, C> then(Consumer<T> action) {
            Predicate<T> predicate = obj -> {
                if (scoreCard.cachedCaseValue == null) {
                    scoreCard.cachedCaseValue = caseExtractor.apply(obj);
                }
                return scoreCard.cachedCaseValue.equals(value);
            };
            scoreCard.addCondition(predicate, action);  // Add the condition to the scoreCard
            return scoreCard;  // Return to the scoreCard for further chaining
        }
    }
}
