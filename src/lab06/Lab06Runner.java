package lab06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lab 6 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab06Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 6.1 Abstract Shape ===");
        Shape[] shapes = { new Circle(2), new Rectangle(3, 4), new Square(5) };
        for (Shape s : shapes) {
            System.out.println(s.describe());
        }

        System.out.println("\n=== Ex 6.2 Flyable: unrelated classes, one interface ===");
        Flyable[] flyers = { new Bird(), new Aeroplane() };
        for (Flyable f : flyers) {
            f.fly();
        }

        System.out.println("\n=== Ex 6.3 Abstract Account ===");
        Account[] accounts = { new SavingsAccount("Craig", 500), new CurrentAccount("Tariro", 500), new SavingsAccount("Farai", 50) };
        for (Account a : accounts) {
            a.deposit(100);
            a.applyMonthlyFee();
            System.out.println(a);
        }

        System.out.println("\n=== Ex 6.4 Comparable (name) vs Comparator (marks) ===");
        List<Student> list = new ArrayList<>(List.of(new Student("Tariro", 78), new Student("Farai", 91), new Student("Chipo", 78)));
        Collections.sort(list);
        System.out.println("By name : " + list);
        list.sort(Student.BY_MARKS_DESC);
        System.out.println("By marks: " + list);

        System.out.println("\n=== Ex 6.5 Conflicting default methods ===");
        new Amphibian().hello();
    }
}
