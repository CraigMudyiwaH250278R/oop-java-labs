package lab05;

/**
 * Exercise 5.3 - Hourly-rate developer.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Developer extends Employee {
    private final double hourlyRate;
    private final int hours;

    public Developer(String name, double hourlyRate, int hours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    @Override
    public double monthlyPay() { return hourlyRate * hours; }
}
