package lab03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Exercise 3.3 - BankAccount with a transaction history.
 * {@link #getHistory()} returns an UNMODIFIABLE COPY, so callers can neither change the
 * account's internal list nor see later changes by accident (defensive copy).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class BankAccount {
    private final String owner;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("opening balance cannot be negative");
        }
        this.owner = owner;
        this.balance = openingBalance;
        history.add(String.format("OPEN    %.2f", openingBalance));
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("deposit must be positive");
        balance += amount;
        history.add(String.format("DEPOSIT %.2f", amount));
    }

    /** @return true when the withdrawal succeeded, false when funds are insufficient. */
    public boolean withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("withdrawal must be positive");
        if (amount > balance) {
            history.add(String.format("DENIED  %.2f", amount));
            return false;
        }
        balance -= amount;
        history.add(String.format("WITHDRAW %.2f", amount));
        return true;
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }

    /** @return an unmodifiable snapshot of the transaction history. */
    public List<String> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }
}
