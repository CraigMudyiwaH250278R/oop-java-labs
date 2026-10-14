package lab06;

/**
 * Exercise 6.5 - Second interface with the SAME default method signature.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public interface Swimmer {
    default void hello() { System.out.println("Hello from Swimmer"); }
}
