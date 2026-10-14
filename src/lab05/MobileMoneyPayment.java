package lab05;

/**
 * Exercise 5.5 - Mobile money payment.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public record MobileMoneyPayment(double amount, String phone) implements Payment {
    @Override
    public String process() {
        return String.format("Mobile money request sent to %s: $%.2f", phone, amount);
    }
}
