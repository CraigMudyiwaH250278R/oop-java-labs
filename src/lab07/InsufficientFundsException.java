package lab07;

/**
 * Exercise 7.4 - Checked exception carrying the data needed to report the shortfall.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class InsufficientFundsException extends Exception {
    private static final long serialVersionUID = 1L;

    private final double shortfall;

    public InsufficientFundsException(double balance, double requested) {
        super(String.format("Insufficient funds: balance %.2f, requested %.2f", balance, requested));
        this.shortfall = requested - balance;
    }

    public double getShortfall() { return shortfall; }
}
