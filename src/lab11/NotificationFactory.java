package lab11;

/**
 * Pattern 2: FACTORY - callers ask for a KIND by name and receive a Notification, never
 * touching concrete classes. Adding a channel means one new class plus one line here.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class NotificationFactory {
    private NotificationFactory() { }

    private record Email(String to) implements Notification {
        public String send(String message) { return "EMAIL to " + to + ": " + message; }
    }

    private record Sms(String to) implements Notification {
        public String send(String message) { return "SMS to " + to + ": " + message; }
    }

    public static Notification create(String type, String recipient) {
        return switch (type.toLowerCase()) {
            case "email" -> new Email(recipient);
            case "sms" -> new Sms(recipient);
            default -> throw new IllegalArgumentException("Unknown notification type: " + type);
        };
    }
}
