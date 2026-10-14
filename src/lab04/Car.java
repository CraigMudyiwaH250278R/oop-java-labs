package lab04;

/**
 * Exercise 4.3 - Middle level of the hierarchy.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Car extends Vehicle {
    protected final int doors;

    public Car(String make, int doors) {
        super(make);
        this.doors = doors;
        System.out.println("2. Car constructor (doors=" + doors + ")");
    }
}
