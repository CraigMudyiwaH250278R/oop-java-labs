package lab04;

/**
 * Lab 4 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab04Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 4.1 + 4.2 Person, Student, Teacher share code ===");
        Person[] people = {
            new Person("Craig", 21),
            new Student("Tariro", 20, "Software Engineering"),
            new Teacher("Mr Moyo", 45, "Algorithms")
        };
        for (Person p : people) {
            p.display();
        }

        System.out.println("\n=== Ex 4.3 Constructor order: Vehicle -> Car -> ElectricCar ===");
        new ElectricCar("Tesla", 4, 75);

        System.out.println("\n=== Ex 4.4 Animal / Dog (fixed with explicit super(name)) ===");
        System.out.println(new Dog("Rex").speak());

        System.out.println("\n=== Ex 4.5 Stack: inheritance vs composition ===");
        BadStack<Integer> bad = new BadStack<>();
        bad.push(1);
        bad.push(2);
        bad.push(3);
        bad.add(0, 99);   // legal on BadStack, but it breaks LIFO!
        System.out.println("BadStack leaked list operations; bottom was changed -> " + bad);

        GoodStack<Integer> good = new GoodStack<>();
        good.push(1);
        good.push(2);
        good.push(3);
        System.out.println("GoodStack peek=" + good.peek() + ", pop=" + good.pop() + ", size=" + good.size());
    }
}
