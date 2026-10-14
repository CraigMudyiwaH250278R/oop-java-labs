package lab07;

/**
 * Exercise 7.3 - Throws {@link InvalidAgeException} outside 0..120.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class AgeValidator {
    private AgeValidator() { }

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException(age);
        }
    }
}
