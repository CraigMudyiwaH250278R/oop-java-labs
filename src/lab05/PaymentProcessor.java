package lab05;

/**
 * Exercise 5.5 - Two designs, side by side.
 *
 * <p><b>Design A - instanceof chain</b>: the processor knows every concrete type. Adding a new
 * payment type (say, BankTransfer) means editing this method, and forgetting a branch compiles
 * fine but fails at run time. Violates the Open/Closed Principle.
 *
 * <p><b>Design B - polymorphic process()</b>: each type carries its own behaviour. The processor
 * is one line and never changes; a new type is one new class. The compiler enforces that every
 * implementation provides process(). Prefer B; use A-style pattern matching only when you cannot
 * modify the types (e.g., library classes).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class PaymentProcessor {
    private PaymentProcessor() { }

    /** Design A: pattern-matching instanceof (Java 16+). */
    public static String payWithInstanceof(Payment p) {
        if (p instanceof CardPayment c) {
            return "[A] Card " + c.cardNumber().substring(c.cardNumber().length() - 4) + " -> " + c.amount();
        } else if (p instanceof MobileMoneyPayment m) {
            return "[A] Mobile " + m.phone() + " -> " + m.amount();
        }
        throw new IllegalArgumentException("Unsupported payment: " + p.getClass().getSimpleName());
    }

    /** Design B: polymorphism. */
    public static String pay(Payment p) {
        return "[B] " + p.process();
    }
}
