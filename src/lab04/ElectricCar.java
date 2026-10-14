package lab04;

/**
 * Exercise 4.3 - Bottom level of the hierarchy.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class ElectricCar extends Car {
    private final int batteryKwh;

    public ElectricCar(String make, int doors, int batteryKwh) {
        super(make, doors);
        this.batteryKwh = batteryKwh;
        System.out.println("3. ElectricCar constructor (battery=" + batteryKwh + " kWh)");
    }
}
