package lab02;

/**
 * Lab 2 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab02Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 2.1 + 2.2 Circle constructors and static counter ===");
        Circle c1 = new Circle(2.5);
        Circle c2 = new Circle();
        System.out.printf("c1 radius=%.1f, c2 radius=%.1f%n", c1.getRadius(), c2.getRadius());
        System.out.println("Circles created: " + Circle.getCount());

        System.out.println("\n=== Ex 2.3 Employee ===");
        Employee e1 = new Employee("Rudo", "Engineering", 1500);
        Employee e2 = new Employee("Tendai", "Finance");
        Employee e3 = new Employee("Chipo");
        Employee e4 = new Employee(e1);
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4 + "   <- copy of e1, new id");
        System.out.println("e1 == e4 ? " + (e1 == e4));

        System.out.println("\n=== Ex 2.4 Initialisation order ===");
        InitOrderDemo.main(args);

        System.out.println("\n=== Ex 2.5 Temperature static factories ===");
        System.out.println("fromCelsius(100)    -> " + Temperature.fromCelsius(100));
        System.out.println("fromFahrenheit(32)  -> " + Temperature.fromFahrenheit(32));
    }
}
