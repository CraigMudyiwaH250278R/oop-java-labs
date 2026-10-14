package lab07;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Exercise 7.1 - Read two integers and divide, handling BOTH bad input and division by zero.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class DivisionExercise {
    private DivisionExercise() { }

    /** @param prompt whether to print input prompts (false for scripted input)
     *  @return the quotient text, or a friendly error message. Never throws for bad user input. */
    public static String divide(Scanner in, boolean prompt) {
        try {
            if (prompt) System.out.print("Enter numerator: ");
            int a = in.nextInt();
            if (prompt) System.out.print("Enter denominator: ");
            int b = in.nextInt();
            return a + " / " + b + " = " + (a / b);            // may throw ArithmeticException
        } catch (InputMismatchException e) {
            in.nextLine();                                     // discard the bad token
            return "Error: please enter whole numbers only.";
        } catch (ArithmeticException e) {
            return "Error: cannot divide by zero.";
        }
    }
}
