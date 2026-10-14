package lab11;

/**
 * Pattern 3: STRATEGY - an interchangeable algorithm (here: price discount).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
@FunctionalInterface
public interface DiscountStrategy {
    double apply(double price);
}
