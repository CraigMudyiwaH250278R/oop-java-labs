package lab07;

/**
 * Exercise 7.4 - withdraw() now THROWS instead of returning false.
 *
 * <p>Trade-offs:
 * <ul>
 *   <li><b>Pro</b>: a failure cannot be silently ignored (an ignored boolean can); the exception
 *       carries a message and the shortfall; the success path stays uncluttered.</li>
 *   <li><b>Pro</b>: being CHECKED, the compiler forces callers to decide what to do.</li>
 *   <li><b>Con</b>: more ceremony (try/catch) for callers; exceptions cost more than a return value;
 *       insufficient funds is an EXPECTED business outcome, and some teams prefer a result type for those.</li>
 * </ul>
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class BankAccount {
    private double balance;

    public BankAccount(double openingBalance) { this.balance = openingBalance; }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }

    public double getBalance() { return balance; }
}
