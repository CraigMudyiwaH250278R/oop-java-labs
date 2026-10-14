package lab02;

/**
 * Exercise 2.5 - Static factory methods and a private constructor.
 *
 * Why static factories can be clearer than overloaded constructors:
 * new Temperature(25.0) is ambiguous (Celsius? Fahrenheit?) and two constructors taking a
 * double cannot even coexist (same signature). fromCelsius(25.0) states the unit in the
 * method name, can validate, can return cached objects, and can have different names
 * for different meanings.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class Temperature {
    private final double celsius;

    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(double c)    { return new Temperature(c); }
    public static Temperature fromFahrenheit(double f) { return new Temperature((f - 32) * 5.0 / 9.0); }

    public double toCelsius()    { return celsius; }
    public double toFahrenheit() { return celsius * 9.0 / 5.0 + 32; }

    @Override
    public String toString() {
        return String.format("%.1f C / %.1f F", toCelsius(), toFahrenheit());
    }
}
