package lab05;

/**
 * Exercise 5.3 - Employee hierarchy (extended from Lab 4's idea). monthlyPay() is the
 * polymorphic operation; the payroll loop never changes when new subclasses appear.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public abstract class Employee {
    protected final String name;

    protected Employee(String name) { this.name = name; }

    public abstract double monthlyPay();

    @Override
    public String toString() {
        return String.format("%-9s %-8s $%,9.2f", getClass().getSimpleName(), name, monthlyPay());
    }
}
