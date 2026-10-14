package lab05;

/**
 * Exercise 5.5 - Card payment.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public record CardPayment(double amount, String cardNumber) implements Payment {
    @Override
    public String process() {
        String masked = "****" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        return String.format("Charged card %s: $%.2f", masked, amount);
    }
}
