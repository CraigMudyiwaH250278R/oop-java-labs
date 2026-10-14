package lab01;

/**
 * Exercise 1.5 - Address, used by {@link Person} (object composition).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Address {
    public final String street;
    public final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
}
