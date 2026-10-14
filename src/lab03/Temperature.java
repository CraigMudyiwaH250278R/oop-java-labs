package lab03;

/**
 * Exercise 3.5 - Stores KELVIN internally and exposes Celsius and Fahrenheit views.
 * Callers only use getCelsius()/getFahrenheit(); if the internal unit changed again, the public
 * API (and therefore every caller) would be unaffected: that is the payoff of encapsulation.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Temperature {
    private static final double ABSOLUTE_ZERO_KELVIN = 0.0;
    private double kelvin;

    private Temperature(double kelvin) {
        setKelvin(kelvin);
    }

    public static Temperature ofCelsius(double c)    { return new Temperature(c + 273.15); }
    public static Temperature ofFahrenheit(double f) { return new Temperature((f - 32) * 5.0 / 9.0 + 273.15); }

    private void setKelvin(double k) {
        if (k < ABSOLUTE_ZERO_KELVIN) {
            throw new IllegalArgumentException("temperature is below absolute zero (-273.15 C / 0 K)");
        }
        this.kelvin = k;
    }

    public double getCelsius()    { return kelvin - 273.15; }
    public double getFahrenheit() { return (kelvin - 273.15) * 9.0 / 5.0 + 32; }

    public void setCelsius(double c) { setKelvin(c + 273.15); }
}
