package lab06;

/**
 * Exercise 6.3 - Current: flat monthly maintenance fee.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class CurrentAccount extends Account {
    public CurrentAccount(String owner, double openingBalance) { super(owner, openingBalance); }

    @Override
    public double monthlyFee() { return 7.00; }
}
