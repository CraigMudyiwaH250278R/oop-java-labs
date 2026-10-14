package lab11;

/**
 * Lab 11 (patterns part) runner: Singleton, Factory, Strategy, Observer.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab11Runner {
    public static void main(String[] args) {
        System.out.println("=== Singleton ===");
        AppConfig c1 = AppConfig.getInstance();
        AppConfig c2 = AppConfig.getInstance();
        System.out.println(c1.describe() + " | same instance? " + (c1 == c2));

        System.out.println("\n=== Factory ===");
        for (String type : new String[] { "email", "sms" }) {
            Notification n = NotificationFactory.create(type, type.equals("email") ? "craig@example.com" : "+263770000000");
            System.out.println(n.send("Lab 11 submitted"));
        }
        try {
            NotificationFactory.create("pigeon", "x");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== Strategy ===");
        Checkout checkout = new Checkout(Checkout.NONE);
        System.out.printf("No discount   : %.2f%n", checkout.total(100));
        checkout.setStrategy(Checkout.TEN_PERCENT);
        System.out.printf("10%% off       : %.2f%n", checkout.total(100));
        checkout.setStrategy(Checkout.FLAT_FIVE_OFF);
        System.out.printf("$5 off        : %.2f%n", checkout.total(100));

        System.out.println("\n=== Observer ===");
        StockTicker ticker = new StockTicker("ZIM", 10.00);
        StockListener logger = (s, o, n) -> System.out.printf("[logger] %s %.2f -> %.2f%n", s, o, n);
        StockListener alert = (s, o, n) -> { if (n > o * 1.10) System.out.println("[alert ] " + s + " jumped more than 10%!"); };
        ticker.subscribe(logger);
        ticker.subscribe(alert);
        ticker.setPrice(10.50);
        ticker.setPrice(12.00);
        ticker.unsubscribe(alert);
        ticker.setPrice(12.10);
    }
}
