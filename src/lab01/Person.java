package lab01;

/**
 * Exercise 1.5 - A Person HAS-A Address. The address may be null.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Person {
    public final String name;
    public Address address;   // may legitimately be null

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    /** Null-safe lookup: returns "unknown" instead of throwing NullPointerException. */
    public String cityOrUnknown() {
        return address == null ? "unknown" : address.city;
    }
}
