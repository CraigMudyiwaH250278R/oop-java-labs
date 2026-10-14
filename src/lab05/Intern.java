package lab05;

/**
 * Exercise 5.3 - NEW subclass added WITHOUT touching Payroll (open for extension, closed for modification).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Intern extends Employee {
    private final double stipend;

    public Intern(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double monthlyPay() { return stipend; }
}
