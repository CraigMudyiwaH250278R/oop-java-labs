package lab04;

/**
 * Exercise 4.3 - Multilevel inheritance: Vehicle -> Car -> ElectricCar.
 * Each constructor prints a line so the call ORDER is visible: parent first, always.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Vehicle {
    protected final String make;

    public Vehicle(String make) {
        this.make = make;
        System.out.println("1. Vehicle constructor (make=" + make + ")");
    }
}
