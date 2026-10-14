package lab06;

/**
 * Exercise 6.2 - Aeroplane implements Flyable; it shares NO superclass with Bird.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Aeroplane implements Flyable {
    @Override
    public void fly() { System.out.println("Aeroplane accelerates down the runway and takes off."); }
}
