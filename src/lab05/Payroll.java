package lab05;

/**
 * Exercise 5.3 - The payroll loop depends ONLY on the Employee type.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class Payroll {
    private Payroll() { }

    public static double total(Employee[] staff) {
        double sum = 0;
        for (Employee e : staff) {
            sum += e.monthlyPay();     // dynamic dispatch picks the right version
        }
        return sum;
    }
}
