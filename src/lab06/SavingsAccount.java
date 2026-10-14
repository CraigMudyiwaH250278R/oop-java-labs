package lab06;

/**
 * Exercise 6.3 - Savings: no fee while the balance stays at or above the minimum.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 100.0;

    public SavingsAccount(String owner, double openingBalance) { super(owner, openingBalance); }

    @Override
    public double monthlyFee() { return balance >= MINIMUM_BALANCE ? 0.0 : 2.50; }
}
