package lab11;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 4: OBSERVER - the subject keeps a list of listeners and notifies all of them when it changes.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class StockTicker {
    private final List<StockListener> listeners = new ArrayList<>();
    private final String symbol;
    private double price;

    public StockTicker(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public void subscribe(StockListener l)   { listeners.add(l); }
    public void unsubscribe(StockListener l) { listeners.remove(l); }

    public void setPrice(double newPrice) {
        double old = price;
        price = newPrice;
        for (StockListener l : List.copyOf(listeners)) {   // copy: listeners may unsubscribe while notified
            l.onPriceChange(symbol, old, newPrice);
        }
    }
}
