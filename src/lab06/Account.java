package lab06;

/**
 * Exercise 6.3 - Template: deposit() is shared, monthlyFee() is decided by each subclass.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public abstract class Account {
    protected double balance;
    private final String owner;

    protected Account(String owner, double openingBalance) {
        this.owner = owner;
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("deposit must be positive");
        balance += amount;
    }

    public abstract double monthlyFee();

    /** Template method: uses the abstract step. */
    public void applyMonthlyFee() { balance -= monthlyFee(); }

    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return String.format("%s[%s, balance=%.2f, fee=%.2f]", getClass().getSimpleName(), owner, balance, monthlyFee());
    }
}
