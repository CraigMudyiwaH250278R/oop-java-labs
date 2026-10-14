package lab09;

import java.util.HashSet;
import java.util.Set;

/**
 * Lab 9 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab09Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 9.1 enum Level + switch ===");
        for (Level l : Level.values()) {
            System.out.println(l + " -> " + l.advice());
        }

        System.out.println("\n=== Ex 9.2 record Student(name, age) ===");
        StudentRecord a = new StudentRecord("Tariro", 20);
        StudentRecord b = new StudentRecord("Tariro", 20);
        System.out.println("toString   : " + a);
        System.out.println("a.name()   : " + a.name());
        System.out.println("a.equals(b): " + a.equals(b) + ", a == b: " + (a == b));

        System.out.println("\n=== Ex 9.3 Book equals/hashCode in a HashSet ===");
        Set<Book> shelf = new HashSet<>();
        System.out.println("add 1st copy: " + shelf.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("add duplicate ISBN: " + shelf.add(new Book("978-0134685991", "Effective Java (2nd copy)")));
        System.out.println("add different book: " + shelf.add(new Book("978-0132350884", "Clean Code")));
        System.out.println("Set size = " + shelf.size());

        System.out.println("\n=== Ex 9.4 LinkedList with nested Node and inner Iterator ===");
        LinkedList<String> list = new LinkedList<>();
        list.add("alpha");
        list.add("beta");
        list.add("gamma");
        for (String s : list) {                     // works because LinkedList is Iterable
            System.out.println(s);
        }
        System.out.println("size = " + list.size());

        System.out.println("\n=== Ex 9.5 enum Coin fewest coins ===");
        for (int amount : new int[] { 41, 99, 5, 0 }) {
            System.out.println(amount + " cents -> " + Coin.fewestCoins(amount));
        }
    }
}
