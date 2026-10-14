package lab11;

/**
 * Pattern 1: SINGLETON - exactly one shared instance.
 * Uses the initialization-on-demand HOLDER idiom: lazy, thread-safe, no explicit locking, because
 * the JVM guarantees class initialisation happens once. The private constructor encapsulates construction.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class AppConfig {
    private final String appName = "OOP Labs";
    private final String version = "1.0";

    private AppConfig() { }

    private static final class Holder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() { return Holder.INSTANCE; }

    public String describe() { return appName + " v" + version; }
}
