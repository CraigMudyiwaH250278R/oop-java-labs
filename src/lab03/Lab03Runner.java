package lab03;

import java.util.ArrayList;
import java.util.List;

/**
 * Lab 3 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab03Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 3.1 + 3.2 Student validation and derived grade ===");
        Student s = new Student("Tariro", 78);
        System.out.println(s);
        try {
            s.setMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        for (int m : new int[] {80, 70, 55, 30}) {
            s.setMarks(m);
            System.out.println(m + " -> " + s.getGrade());
        }

        System.out.println("\n=== Ex 3.3 BankAccount history is an unmodifiable copy ===");
        BankAccount acc = new BankAccount("Craig", 100);
        acc.deposit(50);
        acc.withdraw(30);
        acc.withdraw(500);
        List<String> view = acc.getHistory();
        view.forEach(System.out::println);
        try {
            view.add("HACK +1000000");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify history from outside: UnsupportedOperationException");
        }
        System.out.println("Balance: " + acc.getBalance());

        System.out.println("\n=== Ex 3.4 ImmutablePerson ===");
        List<String> hobbies = new ArrayList<>(List.of("chess", "football"));
        ImmutablePerson p = new ImmutablePerson("Farai", 20, hobbies);
        hobbies.add("sneaky");                     // mutating the ORIGINAL list...
        System.out.println(p + "   <- unaffected by the later add");
        ImmutablePerson older = p.withAge(21);
        System.out.println("original: " + p.getAge() + ", new object: " + older.getAge());

        System.out.println("\n=== Ex 3.5 Temperature stored in kelvin ===");
        Temperature t = Temperature.ofCelsius(25);
        System.out.printf("25 C -> %.1f F%n", t.getFahrenheit());
        try {
            Temperature.ofCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
