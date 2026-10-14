package lab09;

/**
 * Exercise 9.1 - An enum: a fixed, type-safe set of constants (each is a singleton object).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public enum Level {
    LOW, MEDIUM, HIGH;

    /** Switch expression over the enum; the compiler checks all constants are covered. */
    public String advice() {
        return switch (this) {
            case LOW -> "Relax: no action needed.";
            case MEDIUM -> "Keep an eye on it and plan ahead.";
            case HIGH -> "Act now: this needs immediate attention.";
        };
    }
}
