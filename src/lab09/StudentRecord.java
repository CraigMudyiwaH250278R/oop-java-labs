package lab09;

/**
 * Exercise 9.2 - A record: an immutable data carrier. The compiler generates the constructor,
 * accessors (name(), age()), equals, hashCode and toString from the components.
 * (Named StudentRecord to avoid clashing with Student classes from other labs.)
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public record StudentRecord(String name, int age) {
    /** Compact constructor: validation runs on every construction. */
    public StudentRecord {
        if (age < 0) throw new IllegalArgumentException("age cannot be negative");
    }
}
