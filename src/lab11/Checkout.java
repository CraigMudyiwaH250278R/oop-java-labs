package lab11;

/**
 * Pattern 3: STRATEGY - the context HOLDS a strategy (composition over inheritance) and can have
 * it swapped at run time. Lambdas make strategies one-liners.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Checkout {
    private DiscountStrategy strategy;

    public Checkout(DiscountStrategy strategy) { this.strategy = strategy; }

    public void setStrategy(DiscountStrategy strategy) { this.strategy = strategy; }

    public double total(double price) { return strategy.apply(price); }

    public static final DiscountStrategy NONE = p -> p;
    public static final DiscountStrategy TEN_PERCENT = p -> p * 0.90;
    public static final DiscountStrategy FLAT_FIVE_OFF = p -> Math.max(0, p - 5);
}
