package lab09;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Exercise 9.5 - Enum with fields, a constructor, and behaviour.
 * The greedy algorithm (largest coin first) is optimal for this particular coin system
 * (1, 5, 10, 25); it is NOT optimal for every coin system.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int cents;

    Coin(int cents) { this.cents = cents; }

    public int getCents() { return cents; }

    /** @return how many of each coin make up {@code amount} cents using the fewest coins. */
    public static Map<Coin, Integer> fewestCoins(int amount) {
        if (amount < 0) throw new IllegalArgumentException("amount cannot be negative");
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);
        List<Coin> desc = new ArrayList<>(List.of(values()));
        java.util.Collections.reverse(desc);
        int remaining = amount;
        for (Coin c : desc) {
            int n = remaining / c.cents;
            if (n > 0) {
                result.put(c, n);
                remaining -= n * c.cents;
            }
        }
        return result;
    }
}
