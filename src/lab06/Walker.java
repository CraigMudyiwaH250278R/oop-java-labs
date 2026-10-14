package lab06;

/**
 * Exercise 6.5 - First of two interfaces declaring the same default method.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public interface Walker {
    default void hello() { System.out.println("Hello from Walker"); }
}
