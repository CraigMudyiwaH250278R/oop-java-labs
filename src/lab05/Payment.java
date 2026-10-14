package lab05;

/**
 * Exercise 5.5 - A payment with a polymorphic process() method (the better design).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public interface Payment {
    double amount();

    /** Each payment type knows how to process itself. */
    String process();
}
