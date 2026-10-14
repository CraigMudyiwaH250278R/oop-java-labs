package lab07;

import java.util.Scanner;

/**
 * Lab 7 runner. Uses scripted input by default so the output is reproducible; pass {@code -i}
 * to type the values yourself.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab07Runner {
    public static void main(String[] args) {
        boolean interactive = args.length > 0 && args[0].equals("-i");

        System.out.println("=== Ex 7.1 Division with input handling ===");
        String[] scripts = { "10 2\n", "10 0\n", "ten 5\n" };
        if (interactive) {
            System.out.println(DivisionExercise.divide(new Scanner(System.in), true));
        } else {
            for (String s : scripts) {
                System.out.println("[scripted input: " + s.trim() + "]");
                System.out.println(DivisionExercise.divide(new Scanner(s), false));
            }
        }

        System.out.println("\n=== Ex 7.2 ArrayIndexOutOfBoundsException ===");
        int[] data = { 10, 20, 30 };
        System.out.println(ArrayAccessExercise.elementAt(data, 1));
        System.out.println(ArrayAccessExercise.elementAt(data, 5));

        System.out.println("\n=== Ex 7.3 Checked InvalidAgeException ===");
        for (int age : new int[] { 25, -3, 150 }) {
            try {
                AgeValidator.validateAge(age);
                System.out.println(age + " is valid");
            } catch (InvalidAgeException e) {
                System.out.println("Caught: " + e.getMessage());
            }
        }

        System.out.println("\n=== Ex 7.4 withdraw throws InsufficientFundsException ===");
        BankAccount acc = new BankAccount(100);
        try {
            acc.withdraw(40);
            System.out.println("Withdrew 40, balance " + acc.getBalance());
            acc.withdraw(100);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage() + " (short by " + e.getShortfall() + ")");
        }

        System.out.println("\n=== Ex 7.5 return in try + finally ===");
        FinallyDemo.main(args);
    }
}
