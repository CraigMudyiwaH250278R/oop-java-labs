package lab01;

import java.time.Year;

/**
 * Exercise 1.1 / 1.2 - A Car with state (brand, year, mileage) and behaviour.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Car {
    private final String brand;
    private final int year;
    private final int mileage;

    public Car(String brand, int year, int mileage) {
        this.brand = brand;
        this.year = year;
        this.mileage = mileage;
    }

    /** Prints a one-line description of this car. */
    public void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
    }

    /** @return true when the car is more than 25 years old (uses the current year). */
    public boolean isAntique() {
        return isAntique(Year.now().getValue());
    }

    /** Overload taking the reference year, which makes the logic deterministic and testable. */
    public boolean isAntique(int currentYear) {
        return currentYear - year > 25;
    }

    public String getBrand() { return brand; }
    public int getYear()     { return year; }
    public int getMileage()  { return mileage; }
}
