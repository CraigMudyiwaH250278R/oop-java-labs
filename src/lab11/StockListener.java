package lab11;

/**
 * Pattern 4: OBSERVER - the observer interface. The subject depends on THIS abstraction only
 * (loose coupling).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public interface StockListener {
    void onPriceChange(String symbol, double oldPrice, double newPrice);
}
