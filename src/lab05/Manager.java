package lab05;

/**
 * Exercise 5.3 - Salary plus bonus.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Manager extends Employee {
    private final double salary;
    private final double bonus;

    public Manager(String name, double salary, double bonus) {
        super(name);
        this.salary = salary;
        this.bonus = bonus;
    }

    @Override
    public double monthlyPay() { return salary + bonus; }
}
