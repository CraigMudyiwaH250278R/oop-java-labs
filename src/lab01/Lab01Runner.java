package lab01;

/**
 * Lab 1 runner - demonstrates every exercise in order.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab01Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 1.1 Car: three cars ===");
        Car[] cars = {
            new Car("Toyota", 2018, 85_000),
            new Car("Mazda", 1995, 240_000),
            new Car("Honda", 2022, 12_500)
        };
        for (Car c : cars) {
            c.display();
        }

        System.out.println("\n=== Ex 1.2 isAntique (reference year 2026) ===");
        for (Car c : cars) {
            System.out.println(c.getBrand() + " antique? " + c.isAntique(2026));
        }

        System.out.println("\n=== Ex 1.3 Largest of 5 rectangles ===");
        Rectangle[] rects = {
            new Rectangle(2, 3), new Rectangle(4, 5), new Rectangle(1, 10),
            new Rectangle(6, 4), new Rectangle(3, 3)
        };
        System.out.println("Largest: " + Rectangle.largest(rects));

        System.out.println("\n=== Ex 1.4 Aliasing: Rectangle r2 = r1 ===");
        Rectangle r1 = new Rectangle(5, 2);
        Rectangle r2 = r1;            // copies the REFERENCE, not the object
        r2.width = 99;
        System.out.println("r1.width after r2.width = 99 -> " + r1.width);
        System.out.println("r1 == r2 ? " + (r1 == r2));

        System.out.println("\n=== Ex 1.5 Person -> Address -> city ===");
        Person withAddress = new Person("Tariro", new Address("12 Main St", "Harare"));
        System.out.println(withAddress.name + " lives in " + withAddress.address.city);   // chained dot

        Person noAddress = new Person("Farai", null);
        try {
            System.out.println(noAddress.address.city);                                   // address is null
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: address is null");
        }
        System.out.println("Safe version -> " + noAddress.cityOrUnknown());
    }
}
