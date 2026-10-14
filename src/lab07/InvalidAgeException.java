package lab07;

/**
 * Exercise 7.3 - A CHECKED custom exception (extends Exception): callers are forced by the
 * compiler to handle or declare it, which suits recoverable, expected failures such as bad input.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class InvalidAgeException extends Exception {
    private static final long serialVersionUID = 1L;

    private final int age;

    public InvalidAgeException(int age) {
        super("Invalid age " + age + " (must be between 0 and 120)");
        this.age = age;
    }

    public int getAge() { return age; }
}
